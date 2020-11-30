package com.haraguchi.sampleConvAnnotater.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodCallExprGetter {
	private static List<MethodCallExpr> mce = new ArrayList<MethodCallExpr>();
	
	public static List<MethodCallExpr> getMCE() { return MethodCallExprGetter.mce; }
	
	public static void parse(String dirPath) {
		analyzeSyntax(dirPath);
	}
	
	/* analyze sysntax and generate AST */
	private static void analyzeSyntax (String dirPath) {
		try {
			CompilationUnit compilation = JavaParser.parse(new File(dirPath));
			
			VoidVisitorAdapter<?> visitor = new Visitor();
			compilation.accept(visitor, null);
	    } catch (IOException e) {}	
	}
	
	/* if AST visit MethodCallExprGetter, add node of MethodCallExpr to mce */
	private static class Visitor extends VoidVisitorAdapter<Void> {
		@Override
		public void visit (MethodCallExpr node, Void arg) {
			if (node != null) {
				MethodCallExprGetter.mce.add(node);
			}
		}
	}
}