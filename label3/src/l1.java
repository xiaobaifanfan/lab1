
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class l1 {
static int max=1000;

	public static void main(String[] arges) throws IOException{
		System.out.println("请输入文件地址：");
		Scanner sc=new Scanner(System.in);
		//System.out.println("file 的地址是："+sc.nextLine());
		String fileadress=sc.nextLine();
		File file = new File(fileadress);
		String filedetail=txt2String(file);
		String[] tempstr=deal(filedetail);
		List<String> tmp = new ArrayList<String>();  
		for(int i=0;i<tempstr.length;i++){
			if(!tmp.contains(tempstr[i])){
				tmp.add(tempstr[i]);
			}
		}
		System.out.println(tmp);
		int size = tmp.size();  
		String[] arr = (String[])tmp.toArray(new String[size]);//将所有的单词去重
		int len=arr.length;
		int[][] edge=new int[len][len];
		for(int i=0;i<len;i++){
			for(int j=0;j<len;j++)
			{
				if(i==j)
				{
					edge[i][j]=0;
				}else{
					edge[i][j]=max;
				}
			}
		}
		String[] newData1 = Arrays.copyOfRange(tempstr,0,tempstr.length-1);
		String[] newData2= Arrays.copyOfRange(tempstr,1,tempstr.length);
		String zifu=tempstr[0];
	
		for(int h=1;h<tempstr.length;h++){
			zifu=zifu+tempstr[h];
		}
	 
	  for(int h=0;h<newData1.length;h++){
			String tempstring=newData1[h]+newData2[h];
			edge[task_1(newData1[h],arr)][task_1(newData2[h],arr)]=appearNumber(zifu,tempstring);
		}
	  /*for(int a=0;a<newData1.length;a++) {
		  for(int b=0;b<newData1.length;b++) {
			  System.out.print(edge[a][b] );
		  }
		  System.out.println();
	  }*/
	  for(int i=0;i<10;i++){
		  System.out.println("\n****************欢迎来到lab-1********************\n 0.退出程序\n1.有向图展示\n2.查询桥接词\n3.输入新文本\n4.计算最短路径\n5.一个单词到其他节点的最短路径\n6.随机游走\n请选择你所需的功能前面的序号：");
	      Scanner scd = new Scanner(System.in);        
	      System.out.print("请输入你的选择:");
	      int num = scd.nextInt();
	      System.out.print("您输入的是：\t"+num+"\n");
	      switch(num){
	      case 1:{
	    	  GraphViz gv = new GraphViz();
	          gv.addln(gv.start_graph());
	    	  for(int a=0;a<arr.length;a++) {
	    		  for(int b=0;b<arr.length;b++) {
	    			  if(edge[a][b]>0 && edge[a][b]<1000) {
	    				  gv.addln(arr[a]+" -> "+arr[b]+"[label=\""+edge[a][b]+"\"];");
	    			  }
	    		  }
	    	  }
	          gv.addln(gv.end_graph());
	          //System.out.println(gv.getDotSource());
	          String type = "png";
	          File out = new File("tmpout." + type);
	          gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	          Desktop.getDesktop().open(new File("tmpout.png"));
	    	  break;
	      }
	      case 2:{
	    	  	System.out.println("请输入两个要查询的单词：\n第一个单词是：");
	    	  	Scanner sc1 =new Scanner(System.in);
	    	  	String word1=sc1.next();
		 		System.out.println("键盘输入的内容是："+word1);
	    	  	
		 		System.out.println("请输入两个要查询的单词：\n第二个单词是：");
		 		Scanner sc2 =new Scanner(System.in);
		 		String word2=sc2.next();
		 		System.out.println("键盘输入的内容是："+word2);
		 		
		 		String resut2=queryBridgeWords(word1,word2,edge,arr);
	    	  break;
	      }
	      case 3:{
	    	  	 System.out.println("键盘输入files是：");
				 Scanner sc10 =new Scanner(System.in);
				 //System.out.println("键盘输入的内容是："+sc10.nextLine());
				 String str10=sc10.nextLine();
				 String[] temp10=deal(str10);
				 String str13=generateNewText(edge,arr,temp10);
				 System.out.println(" the new files is: "+str13);
	    	  break;
	      }

	      case 4:{
	    	  	System.out.println("键盘输入的word1是：");
				 Scanner sc11 =new Scanner(System.in);
				 String strr1=sc11.next();
				 //System.out.println("键盘输入的内容是："+sc11.next());
				 System.out.println("键盘输入的word2是：");
				 Scanner sc12 =new Scanner(System.in);
				 //System.out.println("键盘输入的内容是："+sc12.next());
				 String strr2=sc12.next();
				 String result4=calcShortestPath(strr1,strr2,arr,edge);
	    	  break;
	      }
	      case 5:{
	    	     System.out.println("键盘输入的word是：");
				 Scanner sc14 =new Scanner(System.in);
				 //System.out.println("键盘输入的内容是："+sc14.next());
				 String strr14=sc14.next();
				 String result15=calcShortestPath(strr14,arr,edge);
	    	  break;
	      }
	      case 6:{
	    	  //System.out.println("键盘输入的起始的单词：");
				 //Scanner sc15 =new Scanner(System.in);
				 //System.out.println("键盘输入的内容是："+sc15.next());
				 //String strr15=sc15.next();
	    	  	int number=new Random().nextInt(len);
	    	  	String strr15=arr[number];
				 int[][] jiance=chushihua(arr);
	    	     String result16=randomWalk(strr15,arr,edge,jiance);
	    	     
	    	    // String string=Arrays.toString(result16);
	    	     break;
	      }
	      case 0:{
	    	  return;
	      }
	       default:{
	    	   System.out.println("default"); 

	    	   break; 
	       }
	      }
	      } 
	      

         
	}

	

	private static int[][] chushihua(String[] arr) {
		int len=arr.length;
		int[][] jiance=new int[len][len];
		for(int i=0;i<len;i++)
			for(int j=0;j<len;j++)
				jiance[i][j]=0;
		return jiance;
	}



	private static String randomWalk(String strr15, String[] arr, int[][] edge, int[][] jiance) {
		int a=task_1(strr15,arr);
		if(a==-1){
			System.out.print(strr15+"--->");
			return null;
		}
		
		List<Integer> c=xiabiao(a,arr.length,edge);
		
			if(c.size()==0){
				return null;
			}else{
				int num=new Random().nextInt(c.size());
				jiance[a][c.get(num)]=jiance[a][c.get(num)]+1;
				strr15=arr[c.get(num)];
				System.out.print(strr15+"--->");
				if(jiance[a][c.get(num)]>1){
					System.out.println("\n有重复的边出现");
					return null;
				}
			    randomWalk(strr15,arr,edge,jiance);
				
			}
			
	
	return null;
	}

	private static String chachong(List<String> d) {
		String[] str = d.toArray(new String[0]);
		String s = Arrays.toString(str);
		if(d.size()>2){
			String temp=d.get(d.size()-1)+d.get(d.size()-2);
			if(appearNumber(s,temp)>1)
				return "false";
		}
		return "true";
	}



	//返回某个元素的下标
	public static List<Integer> xiabiao(int j,int len,int[][] a){
		
		List<Integer> c = new ArrayList<Integer>(); 
			for(int h=len-1;h>-1;h--){
				if(a[j][h]!=0&&a[j][h]!=max)
				{
					c.add(h);
				}
			}
		
		return c;
	}

	//读取文件
	public static String txt2String(File file){
	    StringBuilder result = new StringBuilder();
	    try{
	        BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	        String s = null;
	        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	            result.append(System.lineSeparator()+s);
	        }
	       
	        br.close();    
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return result.toString();
	}
	
	//处理字符串
	public static String[]  deal(String str)
	{
		int num=str.length();
		char[] temp=str.toCharArray();
		for(int i=0;i<num;i++)
		{
			if((int)temp[i]<65||((int)temp[i]>90&&(int)temp[i]<97)||(int)temp[i]>122)
			{
				temp[i]=(char)32;
				
			}
		}
		String str1=new String(temp);
		str1=str1.toLowerCase();//将所有的字母转化为小写字母
		String[] tempstr=str1.split(" ");
		List<String> tmp = new ArrayList<String>();  
        for(String str2:tempstr){  
            if(str2!=null && str2.length()!=0){  
                tmp.add(str2);  
            }  
        }  //去除string数组中的多余的空元素
        tempstr = tmp.toArray(new String[0]); 
		return tempstr;
	}
	 //lab-1
	public static int task_1(String str,String[] temp){
    	for(int i=0;i<temp.length;i++){
    		if(str.equals(temp[i]))
    			{return i;}
    	}
    	return -1;
    }
	//查找字符串个数
	public static int appearNumber(String srcText, String findText) {
	    int count = 0;
	    Pattern p = Pattern.compile(findText);
	    Matcher m = p.matcher(srcText);
	    while (m.find()) {
	        count++;
	    }
	    return count;
	}
	//lab2
	private static String queryBridgeWords(String strr1, String strr2, int[][]edge,String[] arr) {
		 int a=task_1(strr1,arr);
		 int b=task_1(strr2,arr);
		 ArrayList<String> words= new ArrayList<String>(); 
		 for(int i=0;i<arr.length;i++){
			 if(edge[a][i]!=0&&edge[a][i]!=1000){
				 if(edge[i][b]!=0&&edge[i][b]!=1000)
				 {
					 words.add(arr[i]);
				 }
					 
			 }
		 }
		 if(words.size()==0)
			{
				System.out.print(" no  bridgewords from '"+strr1+"'to'"+strr2+"'\n");
			}else{
				System.out.print(" the birdgewords of '"+strr1+"'and'"+strr2+"'is");
				 for(String ss:words){
			            System.out.print(" '"+ss+"'"+" ");  
			            }
				 System.out.print("\n");
			}
		 
	   return null;     
	}

	//lab4
		public static String generateNewText(int[][]edge,String[] arr, String[] temp){
			
			 ArrayList<String> results= new ArrayList<String>(); 
			 for(int i=0;i<temp.length-2;i++){
				String strr1=temp[i];
				String strr2=temp[i+1];
				results.add(temp[i]);
				ArrayList<String> sList= new ArrayList<String>(); 
				sList=findbridge(strr1,strr2,edge,arr);
				if(sList.size()!=0){
					for(String item:sList){
						results.add(item);
					}
				}
			 }
			 
			 int len=temp.length;
			 results.add(temp[len-1]);
			 String result1=null;
			 result1=results.get(0)+" ";
			 for(int i=1;i<results.size();i++){
				 result1=result1+results.get(i)+" ";
			 }
			return result1; 
		}
		
		public static ArrayList<String> findbridge(String strr1,String strr2, int[][]edge,String[]arr){

			 int a=task_1(strr1,arr);
			 int b=task_1(strr2,arr);
			 ArrayList<String> words= new ArrayList<String>(); 
			 for(int i=0;i<arr.length;i++){
				 if(edge[a][i]!=0&&edge[a][i]!=1000){
					 if(edge[i][b]!=0&&edge[i][b]!=1000)
					 {
						 words.add(arr[i]);
					 }
						 
				 }
			 }
			 if(words.size()==0)
				{
					System.out.print(" no  bridgewords from '"+strr1+"'to'"+strr2+"'\n");
				}else{
					System.out.print(" the birdgewords of '"+strr1+"'and'"+strr2+"'is");
					 for(String ss:words){
				            System.out.print(" '"+ss+"'"+" ");  
				            }
					 System.out.print("\n");
				}
			 
		   return words;     
		}

	//计算两个单词之间的最短路径
	private static String calcShortestPath(String strr1, String strr2, String[] arr, int[][] edge) {
			int MAXV=arr.length;
		   int[][] A= new int[MAXV][MAXV];
		   int[][] path=new int[MAXV][MAXV];
		   List<String> tmp = new ArrayList<String>();  
		   int i,j,k;
		   for(i=0;i<MAXV;i++)
		    for(j=0;j<MAXV;j++)
		    {
		        A[i][j]=edge[i][j];
		        path[i][j]=-1;
		    }
		 for(k=0;k<MAXV;k++)
		   { 
			 
		      for(i=0;i<MAXV;i++)
		         for(j=0;j<MAXV;j++){
		        	 
		            if(A[i][j]>(A[i][k]+A[k][j]))
		            {A[i][j] = A[i][k]+A[k][j];
		                  path[i][j] = k;
		                 } 
		    } 
		
		 }
		  int a=task_1(strr1,arr);
		 int b=task_1(strr2,arr);
		 System.out.println(a);
		 System.out.println(b);
		 System.out.println("the shortest length of the two words is "+A[a][b]);
		 System.out.print("the detailed path is:\t");
		 System.out.print(arr[a]+" ");
		 output(a,b,path,arr);
		 return null;
	}


		public static void output(int i,int j,int[][]path,String[] arr){
			
			if(i==j){
				return;
			}
				
			if(path[i][j]==-1){
				System.out.print(arr[j]+" ");}
				else{
					output(i,path[i][j],path,arr);
					output(path[i][j],j,path,arr);
				}
			
		}
		//lab4-2
		private static String calcShortestPath(String strr1, String[] arr, int[][] edge) {
			int MAXV=arr.length;
			   int[][] A= new int[MAXV][MAXV];
			   int[][] path=new int[MAXV][MAXV];
			   List<String> tmp = new ArrayList<String>();  
			   int i,j,k;
			   for(i=0;i<MAXV;i++)
			    for(j=0;j<MAXV;j++)
			    {
			        A[i][j]=edge[i][j];
			        path[i][j]=-1;
			    }
			 for(k=0;k<MAXV;k++)
			   { 
				 
			      for(i=0;i<MAXV;i++)
			         for(j=0;j<MAXV;j++){
			        	 
			            if(A[i][j]>(A[i][k]+A[k][j]))
			            {A[i][j] = A[i][k]+A[k][j];
			                  path[i][j] = k;
			                 } 
			    } 
			
			 }
			 int a=task_1(strr1,arr);
			 for(int h=0;h<arr.length;h++){
				 int b=h;
				 System.out.println("the shortest length of the  words ' "+arr[a]+" ' to '"+arr[b]+" ' is "+A[a][b]);
				 System.out.print("the detailed path is:\t");
				 System.out.print(arr[a]+" ");
				 output(a,b,path,arr); 
				 System.out.println("\n");
				 
			 }
				
			return null;
		}

}
	

