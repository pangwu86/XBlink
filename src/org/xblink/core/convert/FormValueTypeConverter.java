package org.xblink.core.convert;

import org.xblink.core.TransferInfo;
import org.xblink.core.doc.DocReader;
import org.xblink.core.doc.DocWriter;

/**
 * 具有特定格式的转换器。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public abstract class FormValueTypeConverter implements Converter {

	public boolean isSingleValueType() {
		return false;
	}

	private TransferInfo transferInfo;

	private boolean hasTransferInfo = false;

	public void setTransferInfo(TransferInfo transferInfo) {
		this.transferInfo = transferInfo;
		hasTransferInfo = true;
	}

	private void checkTransferInfo() {
		if (!hasTransferInfo) {
			throw new RuntimeException(String.format("当前的转换器[%s]没有设置TransferInfo。", getClass()));
		}
	}

	protected DocReader getDocReader() {
		checkTransferInfo();
		return transferInfo.getDocReader();
	}

	protected DocWriter getDocWriter() {
		checkTransferInfo();
		return transferInfo.getDocWriter();
	}

	protected abstract FormValueTypeConverter clone();

	public FormValueTypeConverter newInstance() {
		FormValueTypeConverter instance = clone();
		instance.setTransferInfo(null);
		return instance;
	}
}
