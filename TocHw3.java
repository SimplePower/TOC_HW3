//Name 洪梓軒
//Student ID F74006111

//一開始先將url裡的資料用BufferedReader 讀進來再存到String裡
//之後存到JSONArray裡進行處理
//用一個for loop來跑近兩萬筆的資料
//用if判斷 鄉鎮市區, 土地區段, 交易年月 是否與我們輸入的有比對到
//如果有對到就把交易金額取出來累加到price
//每比對到一次amount就加一
//最後每一筆資料都比對完, 就計算平均價格存入avg_price, 然後印出來
//測資: http://www.datagarage.io/api/5385b69de7259bb37d925971

import org.json.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Object;

import java.util.ArrayList;
import java.util.List;
import java.net.*;

public class TocHw3 {

	public static void main(String[] args) {
		String str = new String();
		String line = new String();
		int price = 0;
		int amount = 0;
		int avg_price = 0;
        try {  
            URL url = new URL(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));	
            while ((line = br.readLine()) != null) {
            	str += line;              
            }
            
            JSONTokener jt = new JSONTokener(str);                
            JSONArray arr = new JSONArray(jt);    
            int size = arr.length();
            for(int i=0; i<=size; i++){
            	if(arr.getJSONObject(i).getString("鄉鎮市區").equals(args[1]) ){
            		if(arr.getJSONObject(i).getString("土地區段位置或建物區門牌").indexOf(args[2]) != -1){
            			if(Integer.toString(arr.getJSONObject(i).getInt("交易年月")).indexOf(args[3]) != -1){
            				price += arr.getJSONObject(i).getInt("總價元");
            				amount++;
            			}
             		}
            	}
            }              
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (JSONException e) {
        	if(amount==0)	amount=1; //預防除零錯誤
        	avg_price = price/amount;
        	System.out.println(avg_price);
        	//e.printStackTrace();  
        }
	}
}
