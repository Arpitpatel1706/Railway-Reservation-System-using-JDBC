import java.util.*;
import java.sql.*;
import java.text.*;

public class User {
   	

	Admin ad=new Admin();
	Railway r=new Railway();
	String tname,bp,dp;
	int tnum,seat,catnum,ch;
	String[] pname=new String[1000];
	int[] page=new int[1000];
	String[] pgen=new String[1000];
	
	Scanner a=new Scanner(System.in);
	
	int check1(int tnum) throws Exception
	{
		String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; // jdbc driver
            String url = "jdbc:mysql://localhost:3306/Railway"; // mysql url
            String user = "root"; // mysql username
            String pass = "arpit1234"; // mysql passcode
            Class.forName(mysqlJDBCDriver);
           Connection c = DriverManager.getConnection(url, user,
                    pass);
		Statement s=c.createStatement();
		ResultSet r=s.executeQuery("select * from train where tnum='"+tnum+"' ");
		if(r.first())	
		return 1;
		else
		return 0;
	}
			
	void inputreserve() throws Exception
	{
		System.out.print("Enter train number : ");
		tnum=a.nextInt();
		if(check1(tnum)==0)
		{
			System.out.println("Train number doesn't exist");
			r.user_mode();
		}
		
		System.out.print("Enter boarding : ");
		bp=a.next();
		
		System.out.print("Enter destination : ");
		dp=a.next();
		
		System.out.print("Number of seats required : ");
		seat=a.nextInt();
		java.sql.Date dt2=null;
		try
		{
			System.out.print("Enter date of train's journey in (yyyy-mm-dd) format : ");
			String dt=a.next();
			java.util.Date dt1=new SimpleDateFormat("yyyy-MM-dd").parse(dt);
			dt2=new java.sql.Date(dt1.getTime());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
		int j=0;
		int k=0;
		for(int i=0;i<seat;i++)
		{			
			System.out.print("Enter "+(i+1)+" passenger's name : ");
			pname[i]=a.next();
						
			System.out.print("Enter "+(i+1)+" passenger's age : ");
			page[i]=a.nextInt();
			if((page[i]>0 &&page[i]<=12)||(page[i]>=60&&page[i]<120))
			k++;
			if(page[i]<0||page[i]>120)
			{
				j=1;
				System.out.println("Enter a valid age");
			}
			System.out.print("Enter "+(i+1)+" passenger's gender : ");
			pgen[i]=a.next();
		}
		if(j==1)
		return;
		
		System.out.println("Enter the class : ");
		System.out.println("1 - First AC");
		System.out.println("2 - Second AC");
		System.out.println("3 - Third AC");
		System.out.println("4 - Sleeper coach");
		ch=a.nextInt();
		if((ch!=1)&&(ch!=2)&&(ch!=3)&&(ch!=4))
		System.out.println("Choose from above options only");
		
		else		
		{
			String coach;
			if(ch==1)  coach="First AC";
			else if(ch==2)  coach="Second AC";
			else if(ch==3)  coach="Third AC";
			else  coach="Sleeper Coach";
			
			System.out.println("Enter the concession category : ");
			System.out.println("1. Military Personnel");
			System.out.println("2. None");
			catnum=a.nextInt();
			if(catnum!=2&&catnum!=1)
			{
				System.out.println("Choose from above options only");
				r.user_mode();
			}				
			System.out.print("Confirm there is no turning back!!(y/n) ");
			String conf=a.next();
			
			if(conf=="n")
			{
				System.out.println("Your ticket is not booked");
				r.user_mode();
			}
			int fare=reserve(tnum,tname,bp,dp,seat,ch);
			if(fare==0)
			{
				System.out.println("Train number doesn't exist");
				r.user_mode();
			}
			if(catnum==1)
			{				
				System.out.println("Enter how many millitary personnel are traveling and make sure to carry your ID ");
				int mil=a.nextInt();
				System.out.println("Amount to be paid is "+(fare-(((mil*(fare/seat))*0.35)+((k*(fare/seat))*0.5))));					
			}
			else if(catnum==2)
			System.out.println("Amount to be paid is "+(fare-((k*(fare/seat))*0.5)));			
			chart(pname,page,coach,tnum,dt2);					
		}
	}
	int reserve(int tnum,String tname,String bp,String dp,int seat,int ch) 
	{ 
		int flag=0;
		int fare=0;
		try
		{
			String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; // jdbc driver
            String url = "jdbc:mysql://localhost:3306/Railway"; // mysql url
            String user = "root"; // mysql username
            String pass = "arpit1234"; // mysql passcode
            Class.forName(mysqlJDBCDriver);
           Connection c = DriverManager.getConnection(url, user,
                    pass);
			Statement s=c.createStatement();
			ResultSet r=s.executeQuery("select * from train");
			while(r.next())
			{
				if(tnum==r.getInt(1))	
				{			
					flag=1;	
					if(ch==1)
					fare=seat*r.getInt(6);
					else if(ch==2)
					fare=seat*r.getInt(7);
					else if(ch==3)	
					fare=seat*r.getInt(8);
					else			
					fare=seat*r.getInt(9);
					break;
				}
			}					
			if(flag!=0)
			{
				PreparedStatement st=c.prepareStatement("update train set seats=seats-'"+seat+"' where tnum='"+tnum+"' ");
				st.execute();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);	
		}
		if(flag==0)
		return 0;
		else
		return fare;
	}
	void tckt1() throws Exception
	{
		tckt();
		System.out.print("Do you want to continue or return to main menu (y/n) respectively  : ");
		String ch=a.next();
		if(ch.equals("y"))
		{
			r.user_mode();
		}
		else
		{
			r.main_menu();
		}
	}
	
	void tckt() 
	{
		try
		{	
			
			String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; // jdbc driver
            String url = "jdbc:mysql://localhost:3306/Railway"; // mysql url
            String user = "root"; // mysql username
            String pass = "arpit1234"; // mysql passcode
            Class.forName(mysqlJDBCDriver);
           Connection c = DriverManager.getConnection(url, user,
                    pass);
			Statement s1=c.createStatement();
			ResultSet r1=s1.executeQuery("select * from chart order by sno desc limit 1");
			r1.first();		
			Statement s2=c.createStatement();
			ResultSet r2=s2.executeQuery("select * from chart where pnr='"+r1.getLong(1)+"' ");
			r2.first();
			java.util.Date d=new java.util.Date();
			d.setTime(r2.getTimestamp(8).getTime());
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String str7 = df.format(d);		
			
			DateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
			String str8 = dF.format(r2.getDate(9));
			System.out.println("***************************************************************************************");
			System.out.println("PNR Number : "+r2.getLong(1)+"                        "+"Coach : "+r2.getString(6));
			System.out.println("Name : "+r2.getString(2)+"             "+"Age : "+r2.getInt(3)+"     "+"Gender : "+r2.getString(4));
			System.out.println("Status : "+r2.getString(7)+"                           "+"Seat Number : "+r2.getInt(5));	
			while(r2.next())
			{
				System.out.println("Name : "+r2.getString(2)+"             "+"Age : "+r2.getInt(3)+"     "+"Gender : "+r2.getString(4));
				System.out.println("Status : "+r2.getString(7)+"                           "+"Seat Number : "+r2.getInt(5));	
			
			}
			System.out.println("Date of Travelling : "+str8+"                    "+"Booked on : "+str7);	
			System.out.println("***************************************************************************************");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	void chart(String pname[],int page[],String coach,int tnum,java.sql.Date dt2)
	{
		try
		{
			String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; // jdbc driver
            String url = "jdbc:mysql://localhost:3306/Railway"; // mysql url
            String user = "root"; // mysql username
            String pass = "arpit1234"; // mysql passcode
            Class.forName(mysqlJDBCDriver);
           Connection c = DriverManager.getConnection(url, user,
                    pass);
			
			java.util.Date date=new java.util.Date();
			java.sql.Timestamp sqt=new java.sql.Timestamp(date.getTime());
			Statement s2=c.createStatement();
			Statement s1=c.createStatement();
			Statement s3=c.createStatement();
			ResultSet r3=s3.executeQuery("select * from chart order by sno desc limit 1");
			r3.first();
			for(int i=0;i<seat;i++)
			{	
			ResultSet r2=s2.executeQuery("select * from chart order by sno desc limit 1");
			r2.first();
			ResultSet r1=s1.executeQuery("select * from train where tnum='"+tnum+"' and doj='"+dt2+"' ");
			r1.first();
			PreparedStatement st=c.prepareStatement("insert into chart (pnr,name,age,gender,seatno,coach,status,timestamp,dot,tnum) values(?,?,?,?,?,?,?,?,?,?)");
				st.setLong(1,r3.getLong(1)+1);
				st.setString(2,pname[i]);
				st.setInt(3,page[i]);
				st.setString(4,pgen[i]);				
				if(r1.getInt(3)>0)			st.setInt(5,r2.getInt(5)+1);
				else						st.setInt(5,0);
				st.setString(6,coach);
				if(r1.getInt(3)>0)			st.setString(7,"confirmed");
				else						st.setString(7,"waiting");
				st.setTimestamp(8,sqt);
				st.setDate(9,r1.getDate(10));
				st.setInt(10,tnum);				
				st.executeUpdate();		
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("Congrats!!!! Your ticket is booked. Have a nice day!!");
		try
		{
			tckt1();
		}
		catch(Exception e)	
		{
			System.out.println(e);
		}	
	}
	void cancel1() throws Exception
	{
		cancel();
		System.out.print("Do you want to continue or return to main menu (y/n) respectively  : ");
		String ch=a.next();
		if(ch.equals("y"))
		{
			r.user_mode();
		}
		else
		{
			r.main_menu();
		}
	}
		
	
	void cancel() throws Exception
	{
		long pnr;
		String j="cancel";
		String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; // jdbc driver
            String url = "jdbc:mysql://localhost:3306/Railway"; // mysql url
            String user = "root"; // mysql username
            String pass = "arpit1234"; // mysql passcode
            Class.forName(mysqlJDBCDriver);
           Connection c = DriverManager.getConnection(url, user,
                    pass);	
		System.out.print("Enter PNR Number  ");
		pnr=a.nextLong();
		Statement stmt=c.createStatement();
		ResultSet r=stmt.executeQuery("select * from chart where pnr='"+pnr+"' ");
		if(r.first())
		{
			PreparedStatement st=c.prepareStatement("update chart set status='"+j+"' where pnr='"+pnr+"' ");
			st.executeUpdate();
		}
		else
		{	
			System.out.println("PNR number does not exist ");
		}
		
	}	
	void setw(int tnum, String str1, int seats,String str10,String str11, int fAc,int sAc,int tAc,int sc,java.sql.Date doj, String str7,String str9, int width)
	{
		String str=Integer.toString(tnum);
		System.out.print(str);
		for (int x = str.length(); x < width; ++x) 
		System.out.print(' ');	
		System.out.print(str1);		
		for (int x = str1.length(); x < width; ++x) 
		System.out.print(' ');		
		String str8=Integer.toString(seats);
		System.out.print(str8);		
		for (int x = str8.length(); x < width; ++x) 
		System.out.print(' ');		
		System.out.print(str10);		
		for (int x = str10.length(); x < width; ++x) 
		System.out.print(' ');
		System.out.print(str11);		
		for (int x = str11.length(); x < width; ++x) 
		System.out.print(' ');		
		String str2=Integer.toString(fAc);
		System.out.print(str2);
		for (int x = str2.length(); x < width; ++x) 
		System.out.print(' ');	
		String str3=Integer.toString(sAc);
		System.out.print(str3);
		for (int x = str3.length(); x < width; ++x) 
		System.out.print(' ');	
		String str4=Integer.toString(tAc);
		System.out.print(str4);
		for (int x = str4.length(); x < width; ++x) 
		System.out.print(' ');	
		String str5=Integer.toString(sc);
		System.out.print(str5);
		for (int x = str5.length(); x < width; ++x) 
		System.out.print(' ');
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String str6 = df.format(doj);
		System.out.print(str6);
		for (int x = str6.length(); x < width; ++x) 
		System.out.print(' ');
		System.out.print(str7);
		for (int x = str7.length(); x < width; ++x) 
		System.out.print(' ');
		System.out.println(str9);
	}
	void enquiry1() throws Exception
	{
		enquiry();
		System.out.print("Do you want to continue or return to main menu (y/n) respectively  : ");
		String ch=a.next();
		if(ch.equals("y"))
		{
			r.user_mode();
		}
		else
		{
			r.main_menu();
		}
	}
		
	void enquiry() throws Exception
	{
		System.out.print("From ");
		String from=a.next();	
		System.out.print("To ");
		String to=a.next();	
		String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; // jdbc driver
            String url = "jdbc:mysql://localhost:3306/Railway"; // mysql url
            String user = "root"; // mysql username
            String pass = "arpit1234"; // mysql passcode
            Class.forName(mysqlJDBCDriver);
            Connection c = DriverManager.getConnection(url, user,
                    pass);	
		System.out.println("***************************************************************************************************************************************************************************************");
		System.out.println("Train Number   Train Name     Seats          Boarding       Destination    First AC       Second AC      Third AC       Sleeper Coach  Journey date   Departure      Arrival");
		System.out.println("***************************************************************************************************************************************************************************************");

		Statement st=c.createStatement();
		ResultSet r=st.executeQuery("select * from train where bp='"+from+"' and dp='"+to+"' and doj>=CURDATE()");
		while(r.next())
		{
			//tnum*****tname****seats******bp*******dp********fAC****sAC*****tAC******sc***doj*******dtime***atime*****sno
			setw(r.getInt(1),r.getString(2),r.getInt(3),r.getString(4),r.getString(5),r.getInt(6),r.getInt(7),r.getInt(8),r.getInt(9),r.getDate(10),r.getString(11),r.getString(12),15);
			
			
		}
	}
	

}


