package edu.lbz207.help;

public class StringTool {

	

	public static String toMultiLine(String passage) {
		StringBuilder sb = new StringBuilder();
		int len = passage.length();
		int sublen = 50;
		int count = len/sublen;
		int begin=0;
		int end=0;
		for(int i=0;i<count;i++){
			begin=sublen*i;
			end=sublen*(i+1);
			sb.append(passage.subSequence(begin, end)+"\n");
		}
		
		sb.append(passage.subSequence(sublen*count, len)+"\n");
		
		return sb.toString();
	}

	
}
