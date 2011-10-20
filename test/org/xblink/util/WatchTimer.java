package org.xblink.util;

/**
 * 计时工具
 * 
 * @author 胖五(pangwu86@gmail.com)
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

	/**
	 * 获得间隔时间(纳秒)
	 * 
	 * @return 以秒/毫秒/微妙为单位的时间字符串
	 */
	public String getTimer() {
		long span = getSpan();
		return span / 1000000000 + " 秒, " + span / 1000000 + " 毫秒, " + span / 1000 + " 微秒.";
	}

	/**
	 * 获得间隔时间(纳秒)
	 * 
	 * @return 以纳秒为单位的时间数字
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
		//不需要这样转吧??
		return (float) (bytes / 1024) / (float) (getSpan() / (float) (1000 * 1000 * 1000));
	}

	/**
	 * 每秒M字节
	 * 
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
