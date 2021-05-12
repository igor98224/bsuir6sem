package MorozIA.validatorsMorozIA;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateNow {

    public  void dateNow(){
        // Инициализация объекта date
        DateNow date = new DateNow();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // Вывод текущей даты и времени с использованием toString()
        System.out.println(date1);
    }
}
