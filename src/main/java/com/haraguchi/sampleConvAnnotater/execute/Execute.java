package com.haraguchi.sampleConvAnnotater.execute;

import com.haraguchi.sampleConvAnnotater.findTarget.FindTarget;

public class Execute {
	private static final String targetFile = "/Users/Yushi/Documents/Kohno/sampleCode.java";
	
	static public void main(String[] args) {
		FindTarget.execute(targetFile);
	}
}