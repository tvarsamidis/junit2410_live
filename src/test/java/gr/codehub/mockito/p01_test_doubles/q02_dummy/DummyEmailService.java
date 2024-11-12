package gr.codehub.mockito.p01_test_doubles.q02_dummy;

public class DummyEmailService implements EmailService {
    @Override
    public void sendEmail(String message) {
        throw new AssertionError("Method not implemented !!!");
    }
}
