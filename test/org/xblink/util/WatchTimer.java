package org.xblink.util;

/**
 * 计时工具类
 * 
 * @author geor.yuli
 * 
 *         example: WatchTimer timer = new WatchTimer(); ... long span =
 *         timer.getSpan(); String spanMsg = timer.getTimer();
 * 
 *         reset: timer.reset();
 */
public class WatchTimer {

	/**
	 * 计时开始时刻
	 */
	private long beginerNano;

	/**
	 * 字节数（用于测速度）
	 */
	private long bytes;

	public WatchTimer() {
		reset();
	}

	public void reset() {
		beginerNano = System.nanoTime();
	}

	public String getTimer() {
		long span = getSpan();
		return span / 1000 + " 微秒, " + span / 1000000 + " 毫秒";
	}

	/**
	 * 间隔时间(纳秒)
	 * 
	 * @return
	 */
	public long getSpan() {
		long currentTime = System.nanoTime();
		return currentTime - beginerNano;
	}

	/**
	 * 每秒K字节
	 * 
	 * @return
	 */
	public float getSpeedKPerSecond() {
		return (float) (bytes / 1024) / (float) (getSpan() / (float)(1000 * 1000 * 1000));
	}
	
	/**
	 * 每秒M字节
	 * @return
	 */
	public float getSpeedMPerSecond() {
		return getSpeedKPerSecond() / 1024F;
	}

	public long getBytes() {
		return bytes;
	}

	public void setBytes(long bytes) {
		this.bytes = bytes;
	}

}
