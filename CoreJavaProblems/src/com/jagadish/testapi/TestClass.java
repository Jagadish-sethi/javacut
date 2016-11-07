package com.jagadish.testapi;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestClass {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		AtomicInteger integer = new AtomicInteger();
		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();
		String unicodeMessage =
                "\u2654 " + // white king
                "\u2655 " + // white queen
                "\u2656 " + // white rook
                "\u2657 " + // white bishop
                "\u2658 " + // white knight
                "\u2659 " + // white pawn
                "\n" +
                "\u265A " + // black queen
                "\u265B " + // black queen
                "\u265C " + // black rook
                "\u265D " + // black bishop
                "\u265E " + // black knight
                "\u265F " + // black pawn
                "\n" ;
//PrintStream out = new PrintStream (System.out, true , "UTF8" );
//out.println(unicodeMessage);
		
		System.out.println(unicodeMessage);
		
		
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
	}

}


