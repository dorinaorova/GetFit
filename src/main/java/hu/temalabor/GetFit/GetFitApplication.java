package hu.temalabor.GetFit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GetFitApplication {


	public static void main(String[] args) {
		SpringApplication.run(GetFitApplication.class, args);

//		Calendar calendarka =Calendar.getInstance() ;
//		calendarka.setTime(Timestamp.valueOf("2021-11-27 00:00:00.0"));
//		System.out.println(calendarka);
//		int days= calendarka.get(Calendar.DAY_OF_WEEK)+5;
//		if(days==1) days+=5;
//		else days-=7;
//		System.out.println(days);
//		calendarka.add(Calendar.DATE,-days); //first day of week
//		System.out.println("first day of week: "+calendarka);
//
//			Calendar cal = Calendar.getInstance();
//			Timestamp ts = Timestamp.valueOf("2021-11-25 00:00:00.0");
//			cal.setTime(new Date(ts.getTime()));
//			//cal.add(Calendar.DATE, -7); //8?
//
//			if(calendar.before(cal)){
//				calendar.add(Calendar.DATE, 7); //9?
//				if(calendar.after(cal)) System.out.println("nopeeee");
//			}
//
//
//		Calendar calendar = Calendar.getInstance();
//		calendar.setFirstDayOfWeek(Calendar.MONDAY);
//		calendar.setTime( new Timestamp(1638107576288L));
//		System.out.println(calendar);
//		int days= calendar.get(Calendar.DAY_OF_WEEK);
//		System.out.println(days);
//		if (days==1) days+=7;
//		days-=calendar.getFirstDayOfWeek();
//		System.out.println(days);
//		calendar.add(Calendar.DATE,-days); //first day of week
//		System.out.println(calendar);
//
//			Calendar cal = Calendar.getInstance();
//			Timestamp ts = new Timestamp(1638107576288L); //the day when the goal started
//			cal.setTime(new Date(ts.getTime()));
//
//			if(calendar.before(cal)){
//				calendar.add(Calendar.DATE, 7); //9?
//				if(calendar.after(cal))  System.out.println("hehe");
//			}


	}



}
