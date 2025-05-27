package cn.apkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class IahmsApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(IahmsApplication.class, args);
		// 打印启动成功信息
        System.out.println("""
				(♥◠‿◠)ﾉﾞ  IA启动成功   ლ(´ڡ`ლ)ﾞ \s
				 .-------.       ____     __       \s
				 |  _ _   \\      \\   \\   /  /   \s
				 | ( ' )  |       \\  _. /  '      \s
				 |(_ o _) /        _( )_ .'        \s
				 | (_,_).' __  ___(_ o _)'         \s
				 |  |\\ \\  |  ||   |(_,_)'        \s
				 |  | \\ `'   /|   `-'  /          \s
				 |  |  \\    /  \\      /          \s
				 ''-'   `'-'    `-..-'             \s
		""");
    }
}