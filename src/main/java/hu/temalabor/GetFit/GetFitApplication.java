package hu.temalabor.GetFit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GetFitApplication {


	public static void main(String[] args) {
		SpringApplication.run(GetFitApplication.class, args);

//		Calendar calendar =Calendar.getInstance() ;
//		calendar.setTime(Timestamp.valueOf("2021-11-27 00:00:00.0"));
//		System.out.println(calendar);
//		int days= calendar.get(Calendar.DAY_OF_WEEK);
//		System.out.println(days);
//		calendar.add(Calendar.DATE,-days+calendar.getFirstDayOfWeek()); //first day of week
//		System.out.println("first day of week: "+calendar);
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

	}



}
