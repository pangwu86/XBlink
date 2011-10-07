package org.xblink.core.doc.impl;

import java.io.Reader;
import java.util.Iterator;

import org.xblink.core.doc.AbstractDocReader;
import org.xblink.rep.org.xmlpull.mxp1.MXParser;
import org.xblink.rep.org.xmlpull.v1.XmlPullParser;

/**
 * XML格式Reader，基于XPP3的MXParser实现。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public class XPP3Reader extends AbstractDocReader {

	protected static final int START_NODE = 1;
	protected static final int END_NODE = 2;
	protected static final int TEXT = 3;
	protected static final int COMMENT = 4;
	protected static final int OTHER = 0;

	private final FastStack elementStack = new FastStack(16);
	private final FastStack pool = new FastStack(16);

	private final FastStack lookahead = new FastStack(4);
	private final FastStack lookback = new FastStack(4);
	private boolean marked;

	private static class Event {
		int type;
		String value;
	}

	public static final class FastStack {

		private Object[] stack;
		private int pointer;

		public FastStack(int initialCapacity) {
			stack = new Object[initialCapacity];
		}

		public Object push(Object value) {
			if (pointer + 1 >= stack.length) {
				resizeStack(stack.length * 2);
			}
			stack[pointer++] = value;
			return value;
		}

		public void popSilently() {
			stack[--pointer] = null;
		}

		public Object pop() {
			final Object result = stack[--pointer];
			stack[pointer] = null;
			return result;
		}

		public Object peek() {
			return pointer == 0 ? null : stack[pointer - 1];
		}

		public Object replace(Object value) {
			final Object result = stack[pointer - 1];
			stack[pointer - 1] = value;
			return result;
		}

		public void replaceSilently(Object value) {
			stack[pointer - 1] = value;
		}

		public int size() {
			return pointer;
		}

		public boolean hasStuff() {
			return pointer > 0;
		}

		public Object get(int i) {
			return stack[i];
		}

		private void resizeStack(int newCapacity) {
			Object[] newStack = new Object[newCapacity];
			System.arraycopy(stack, 0, newStack, 0, Math.min(pointer, newCapacity));
			stack = newStack;
		}

		public String toString() {
			StringBuffer result = new StringBuffer("[");
			for (int i = 0; i < pointer; i++) {
				if (i > 0) {
					result.append(", ");
				}
				result.append(stack[i]);
			}
			result.append(']');
			return result.toString();
		}
	}

	@SuppressWarnings("hiding")
	public static class AttributeNameIterator<String> implements Iterator<String> {

		private int current;
		private final int count;
		private final XPP3Reader reader;

		public AttributeNameIterator(XPP3Reader reader) {
			this.reader = reader;
			count = reader.getAttributeCount();
		}

		public boolean hasNext() {
			return current < count;
		}

		@SuppressWarnings("unchecked")
		public String next() {
			return (String) reader.getAttributeName(current++);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private XmlPullParser parser;

	public XPP3Reader(Reader reader) {
		super(reader);
		parser = new MXParser();
		try {
			parser.setInput(reader);
		} catch (Exception e) {
			throw new RuntimeException("XPP3Reader无法初始化。", e);
		}
	}

	public String getTextValue() {
		String last = null;
		StringBuffer buffer = null;
		mark();
		Event event = readEvent();
		while (true) {
			if (event.type == TEXT) {
				String text = event.value;
				if (text != null && text.length() > 0) {
					if (last == null) {
						last = text;
					} else {
						if (buffer == null) {
							buffer = new StringBuffer(last);
						}
						buffer.append(text);
					}
				}
			} else if (event.type != COMMENT) {
				break;
			}
			event = readEvent();
		}
		reset();
		if (buffer != null) {
			return buffer.toString();
		} else {
			return (last == null) ? "" : last;
		}
	}

	public Iterator<String> getAttributeNames() {
		return new AttributeNameIterator<String>(this);
	}

	public boolean hasMoreChildren() {
		mark();
		while (true) {
			switch (readEvent().type) {
			case START_NODE:
				reset();
				return true;
			case END_NODE:
				reset();
				return false;
			default:
				continue;
			}
		}
	}

	public void moveDown() {
		int currentDepth = elementStack.size();
		while (elementStack.size() <= currentDepth) {
			move();
			if (elementStack.size() < currentDepth) {
				throw new RuntimeException(); // sanity check
			}
		}
	}

	public void moveUp() {
		int currentDepth = elementStack.size();
		while (elementStack.size() >= currentDepth) {
			move();
		}
	}

	private void move() {
		final Event event = readEvent();
		pool.push(event);
		switch (event.type) {
		case START_NODE:
			elementStack.push(pullElementName());
			break;
		case END_NODE:
			elementStack.pop();
			break;
		}
	}

	private Event readEvent() {
		if (marked) {
			if (lookback.hasStuff()) {
				return (Event) lookahead.push(lookback.pop());
			} else {
				return (Event) lookahead.push(readRealEvent());
			}
		} else {
			if (lookback.hasStuff()) {
				return (Event) lookback.pop();
			} else {
				return readRealEvent();
			}
		}
	}

	private Event readRealEvent() {
		Event event = pool.hasStuff() ? (Event) pool.pop() : new Event();
		event.type = pullNextEvent();
		if (event.type == TEXT) {
			event.value = pullText();
		} else if (event.type == START_NODE) {
			event.value = pullElementName();
		} else {
			event.value = null;
		}
		return event;
	}

	public void mark() {
		marked = true;
	}

	public void reset() {
		while (lookahead.hasStuff()) {
			lookback.push(lookahead.pop());
		}
		marked = false;
	}

	public String getNodeName() {
		return (String) elementStack.peek();
	}

	protected int pullNextEvent() {
		try {
			switch (parser.next()) {
			case XmlPullParser.START_DOCUMENT:
			case XmlPullParser.START_TAG:
				return START_NODE;
			case XmlPullParser.END_DOCUMENT:
			case XmlPullParser.END_TAG:
				return END_NODE;
			case XmlPullParser.TEXT:
				return TEXT;
			case XmlPullParser.COMMENT:
				return COMMENT;
			default:
				return OTHER;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String pullElementName() {
		return parser.getName();
	}

	protected String pullText() {
		return parser.getText();
	}

	public String getAttribute(String name) {
		return parser.getAttributeValue(null, name);
	}

	public String getAttribute(int index) {
		return parser.getAttributeValue(index);
	}

	public int getAttributeCount() {
		return parser.getAttributeCount();
	}

	public String getAttributeName(int index) {
		return parser.getAttributeName(index);
	}

	public void close() {

	}

}
