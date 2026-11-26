package mylab.notification.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    public void testDependencyInjection() {

        // NotificationManager 주입 확인
        assertNotNull(notificationManager);

        // 이메일 서비스 검증
        assertNotNull(notificationManager.getEmailService());

        EmailNotificationService emailService =
                (EmailNotificationService) notificationManager.getEmailService();

        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        // SMS 서비스 검증
        assertNotNull(notificationManager.getSmsService());

        SmsNotificationService smsService =
                (SmsNotificationService) notificationManager.getSmsService();

        assertEquals("SKT", smsService.getProvider());
    }

    @Test
    public void testSendNotification() {

        // 이메일 알림 전송
        notificationManager.sendNotificationByEmail("테스트 이메일");

        // SMS 알림 전송
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}