package com.haraguchi.sampleConvAnnotater.findTarget;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.haraguchi.sampleConvAnnotater.parser.MethodCallExprGetter;

public class FindTarget {
	public static List<MethodCallExpr> execute(String targetFile) {
		MethodCallExprGetter.parse(targetFile);
		List<MethodCallExpr> nodes = MethodCallExprGetter.getMCE();
		List<MethodCallExpr> targetNodes = new ArrayList<MethodCallExpr>();

		for(int i = 0; i < nodes.size() ; i++ ) {
			if(nodes.get(i).getName().toString().equals("findViewById")) {
				if((Object)nodes.get(i).getParentNode().get() instanceof CastExpr) {
					System.out.println("OK. Casted: " + nodes.get(i).getParentNode().get().toString());
					System.out.flush();
				} else {
					targetNodes.add(nodes.get(i));
					System.err.println("This may not be converted correctly, Because it has not been cast.");
					System.err.println("\tLocation: " + nodes.get(i).getRange().get());
					System.err.println("\tTarget: " + nodes.get(i).toString());
					System.err.flush();
				}
			}
		}
		
		return targetNodes;
	}
}
