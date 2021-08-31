import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Assert;
import org.junit.Test;
import ua.project.model.entity.*;
import ua.project.model.services.ExhibitionService;
import ua.project.model.services.TicketService;
import ua.project.model.services.UserService;
import ua.project.view.ILocaleNames;
import ua.project.view.ITextsPaths;
import ua.project.view.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServletTest {
    private UserService userService = new UserService();

    private ExhibitionService exhibitionService = new ExhibitionService();

    private TicketService ticketService = new TicketService();

    private final static String path = "/WEB-INF/view/index.jsp";

    public ServletTest() {
    }

    @Test
    public void requestTest() {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

    }



    @Test
    public void loadAndSaveUserTest() throws SQLIntegrityConstraintViolationException {
        User user = new User("Olivia", "pass", "olivia@gmail.com", Role.USER);
        userService.saveNewUser(user);
        Assert.assertEquals("Olivia", userService.login("Olivia").get().getLogin());
    }

    @Ignore
    @Test
    public void exhibitionAllTest() {
        Assert.assertNotNull("Exhibitions not found", exhibitionService.findAll());
    }

    @Ignore
    @Test
    public void saveAndFindExhibition() throws SQLIntegrityConstraintViolationException {
        exhibitionService.saveNewExhibition(new Exhibition("Name", 2, LocalDate.now()
                , LocalDate.now().plusDays(5), LocalTime.now(), LocalTime.now().plusHours(8),
                500, ExhibitionState.CANCELED));
        Assert.assertEquals(exhibitionService.findById(32).get().getTopic(), "Name");
    }

    @Ignore
    @Test
    public void planExhibition() {
        exhibitionService.planById(32);
        Assert.assertSame("States are not same", ExhibitionState.PLANNED,
                exhibitionService.findById(32).orElseThrow(IllegalArgumentException::new).getState());
    }

    @Ignore
    @Test
    public void cancelExhibition() {
        exhibitionService.cancelById(32);
        Assert.assertSame("States are not same", ExhibitionState.CANCELED,
                exhibitionService.findById(32).orElseThrow(IllegalArgumentException::new).getState());
    }

    @Ignore
    @Test
    public void saveTicketTest() throws SQLIntegrityConstraintViolationException {
        ticketService.saveNewTicket(new Ticket("olivia@gmail.com", 23, "Name", 32));
        Assert.assertEquals(2, ticketService.countByExhibitionId(32));
    }

    @Ignore
    @Test
    public void countExhibitionsTickets() {
        Assert.assertEquals("Found amount was not equal to given", 2, ticketService.countByExhibitionId(32));
    }

    @Ignore
    @Test
    public void viewChangeLocaleTest() {
        View.view.changeLocale(Optional.of(new Locale(ILocaleNames.UKR_LANGUAGE, ILocaleNames.UKR_COUNTRY)));
        Assert.assertTrue("Could not change locale.", View.view.getBundleText(ITextsPaths.SUBMIT).equals("Ввести"));
    }

}
