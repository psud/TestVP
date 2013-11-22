package com.patsud.bwsvplan;

import java.util.Calendar;

import android.util.Log;

public class DateToNum {
	
	private String todayNumber;

	private void Generator() {

		// GetDate
		final Calendar c = Calendar.getInstance();

		int weekday = (c.get(Calendar.DAY_OF_WEEK));
		int dayOfMonth = (c.get(Calendar.DATE));
		int month = (c.get(Calendar.MONTH));
		int year = (c.get(Calendar.YEAR));
		int weekOfYear = (c.get(Calendar.WEEK_OF_YEAR));
		int weekOfMonth = (c.get(Calendar.WEEK_OF_MONTH));
		
		
		StringBuilder dateString = new StringBuilder();
		dateString.append(weekday).append(dayOfMonth).append(month).append(year).append(weekOfYear).append(weekOfMonth);
		String normalDateStr = dateString.toString();
		int codeLength = normalDateStr.length();
		Log.d("DEBUG", "code Length: " +Integer.toString(codeLength));
		
		String s0to4 = normalDateStr.substring(0, 4); 				Log.d("DEBUG", "s0to4: "+ s0to4);
		String s3to6 = normalDateStr.substring(3, 6);				Log.d("DEBUG", "s3to6: "+ s3to6);
		String s5to8 = normalDateStr.substring(5, 8);				Log.d("DEBUG", "s5to8: "+ s5to8);
		String s7toEnd = normalDateStr.substring(7, codeLength); 	Log.d("DEBUG", "s7toEnd: "+ s7toEnd);
		
		String divideFirst2 = Integer.toString(Integer.parseInt(s0to4)/Integer.parseInt(s3to6));
		Log.d("DEBUG", "divideFirst2: "+ divideFirst2); 
		String subtractDF2fromS5to8 = Integer.toString(Integer.parseInt(s5to8)-Integer.parseInt(divideFirst2));
		Log.d("DEBUG", "subtractDF2fromS5to8: "+ subtractDF2fromS5to8); 
		String s7toEndAddS5to8 = Integer.toString(Integer.parseInt(s5to8)+Integer.parseInt(s7toEnd));
		Log.d("DEBUG", "s7toEndAddS5to8: "+ s7toEndAddS5to8);
		
		StringBuilder randomDateStr = new StringBuilder();
		randomDateStr.append(s7toEndAddS5to8).append(normalDateStr).append(divideFirst2).append(codeLength).append(subtractDF2fromS5to8);
		String randromDateStringS = randomDateStr.toString();
		
		Integer first9 = Integer.parseInt(randromDateStringS.substring(0, 9))/5;
		first9 = first9 * 3;
		Log.d("DEBUG", "randomDateStr:" + randomDateStr.toString());
		Log.d("DEBUG", "first9:" + Integer.toString(first9));
		
		String almostFinalStr = first9 + randromDateStringS;
		Log.d("DEBUG", "almostFinalStr:" + almostFinalStr);
		
		
		String finalCode = "";
		for (int i = 0; i < almostFinalStr.length(); i++){
			if (i % 3 == 0){
				finalCode += almostFinalStr.substring(i, i+1);
			}
		}
		Log.d("DEBUG", "finalCode:" + finalCode);
		
		todayNumber = finalCode;
	}

	public void CreateCode() {
		// TODO Auto-generated method stub
		Generator();
	}

	public String GetCodeLenth() {
		// TODO Auto-generated method stub
		return Integer.toString(todayNumber.length());
	}

	public boolean CheckCode(String string) {
		// TODO Auto-generated method stub
		if (string.equalsIgnoreCase(todayNumber))
			return true;
		else
			return false;
	}

}
