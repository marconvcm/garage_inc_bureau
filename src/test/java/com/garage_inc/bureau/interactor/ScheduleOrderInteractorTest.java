package com.garage_inc.bureau.interactor;


import com.garage_inc.bureau.component.SendMessageProvider;
import com.garage_inc.bureau.model.Order;
import com.garage_inc.bureau.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ScheduleOrderInteractorTest {

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    SendMessageProvider sendMessageProvider;

    @Test
    public void testRunWithSuccess() throws Exception {
        // Given
        when(orderRepository.findFirstByScheduledDateTime(any())).thenReturn(Optional.empty());
        when(orderRepository.save(any())).then((param) -> {
            Order order = param.getArgument(0);
            order.setId(10);
            return order;
        });
        ScheduleOrderInteractor.Input input = new ScheduleOrderInteractor.Input() {{
            setScheduledDateTime(LocalDateTime.now());
        }};
        ScheduleOrderInteractor target = new ScheduleOrderInteractor(orderRepository, sendMessageProvider);

        // When
        ScheduleOrderInteractor.Output output = target.run(input);

        // Then
        assertEquals(output.getOrder().getId(), 10);
    }

    @Test
    public void testRunWithError() throws Exception {

        LocalDateTime asOf = LocalDateTime.now();

        Order stubOrder = new Order() {{
            setScheduledDateTime(asOf.plusMinutes(30));
        }};

        // Given
        when(orderRepository.findFirstByScheduledDateTime(any())).thenReturn(Optional.of(stubOrder));
        when(orderRepository.save(any())).then((param) -> {
            Order order = param.getArgument(0);
            order.setId(10);
            return order;
        });
        ScheduleOrderInteractor.Input input = new ScheduleOrderInteractor.Input() {{
            setScheduledDateTime(asOf);
        }};

        ScheduleOrderInteractor target = new ScheduleOrderInteractor(orderRepository, sendMessageProvider);

        // When
        try {
            ScheduleOrderInteractor.Output output = target.run(input);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof ScheduleOrderInteractor.OrderAlreadyBookedOnSameRangeOfTimeException);
        }
    }
}