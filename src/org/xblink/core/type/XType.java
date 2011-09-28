package org.xblink.core.type;

import org.xblink.core.AnalysisObject;
import org.xblink.core.TransferInfo;

/**
 * 根据Java对象特点，将不同的对象分成几类，分别处理。
 * 
 * @author 胖五(pangwu86@gmail.com)
 */
public interface XType {

	public void writeItem(Object obj, AnalysisObject analysisObject, TransferInfo transferInfo) throws Exception;

}
