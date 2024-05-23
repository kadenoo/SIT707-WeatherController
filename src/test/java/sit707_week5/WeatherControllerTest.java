package sit707_week5;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

public class WeatherControllerTest {

    private static WeatherController controller;
    private static double[] hourlyTemperatures;

    @BeforeClass
    public static void setUp() throws Exception {
        // Arrange
        controller = WeatherController.getInstance();
        int nHours = controller.getTotalHours();
        hourlyTemperatures = new double[nHours];
        for (int i = 0; i < nHours; i++) {
            hourlyTemperatures[i] = controller.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        controller.close();
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "your_student_id";
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "your_name";
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperatureMin() {
        System.out.println("+++ testTemperatureMin +++");

        // Act
        double minTemperature = Double.MAX_VALUE;
        for (double temp : hourlyTemperatures) {
            if (minTemperature > temp) {
                minTemperature = temp;
            }
        }

        // Assert
        Assert.assertEquals(minTemperature, controller.getTemperatureMinFromCache(), 0.001);
    }

    @Test
    public void testTemperatureMax() {
        System.out.println("+++ testTemperatureMax +++");

        // Act
        double maxTemperature = Double.MIN_VALUE;
        for (double temp : hourlyTemperatures) {
            if (maxTemperature < temp) {
                maxTemperature = temp;
            }
        }

        // Assert
        Assert.assertEquals(maxTemperature, controller.getTemperatureMaxFromCache(), 0.001);
    }

    @Test
    public void testTemperatureAverage() {
        System.out.println("+++ testTemperatureAverage +++");

        // Act
        double sumTemperature = 0;
        for (double temp : hourlyTemperatures) {
            sumTemperature += temp;
        }
        double averageTemperature = sumTemperature / hourlyTemperatures.length;

        // Assert
        Assert.assertEquals(averageTemperature, controller.getTemperatureAverageFromCache(), 0.001);
    }
}
