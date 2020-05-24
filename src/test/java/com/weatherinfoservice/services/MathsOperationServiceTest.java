package com.weatherinfoservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.weatherinfoservice.exceptions.NotImplementedOperationTypeException;
import com.weatherinfoservice.model.MathsInputRequest;
import com.weatherinfoservice.model.OperationType;

@ExtendWith(SpringExtension.class)
class MathsOperationServiceTest {

	@InjectMocks
	MathsOperationService mathsOperationService;

	@InjectMocks
	MathsInputRequest mathsInputRequest;

	@ParameterizedTest
	@MethodSource("getTestPerformScenarioArguments")
	@DisplayName("Test for performing some Maths Operation based on the inputs")
	void testPerform(double firstNumber, double secondNumber, OperationType operationType) throws Exception {
		// MathsOperationService mathsOperationService = new MathsOperationService();
		mathsInputRequest = new MathsInputRequest(firstNumber, secondNumber, operationType);
		switch(operationType)
		{
		case SUM:
			assertEquals(59.73, mathsOperationService.perform(mathsInputRequest),
					"This method should perform the maths operation of operation type Sum");
			break;
			
		case MULTIPLY:
			assertEquals(75.0, mathsOperationService.perform(mathsInputRequest),
					"This method should perform the maths operation of operation type Multiplication");
			break;
			
		case DIVIDE:
			assertEquals(0.4, mathsOperationService.perform(mathsInputRequest),
					"This method should perform the maths operation of operation type Division");
			break;
			
		default:
			//given(mathsOperationService.perform(mathsInputRequest)).willThrow(NotImplementedOperationTypeException.class);
			assertThrows(NotImplementedOperationTypeException.class, ()->mathsOperationService.perform(mathsInputRequest));
			//fail("Not implemented yet...so failing for Operation type:: " +operationType.name());
			break;
		}
		
	}

	@ParameterizedTest
	@MethodSource("getTestShowOperationResultScenarioArguments")
	@DisplayName("Test for showing Maths Operation result Based On Operation Type")
	void testShowOperationResult(double result, OperationType operationType) throws Exception {
		switch(operationType)
		{	
	case SUM:
		assertEquals(
				"Sum is:: " +29.25, mathsOperationService
				.showOperationResult(result, operationType),
		"This method should display the message after performing Maths Operation Division");
		break;
		
	case MULTIPLY:
		assertEquals(
				"Multiplication is:: " + 75.0, mathsOperationService
				.showOperationResult(result, operationType),
		"This method should display the message after performing Maths Operation Multiplication");
		break;
		
	case DIVIDE:
		assertEquals(
				"Division is:: " +4.5, mathsOperationService
				.showOperationResult(result, operationType),
		"This method should display the message after performing Maths Operation Division");
		break;
		
		default:
			break;
		
		}
		

	}

	
	  private static Stream<Arguments> getTestPerformScenarioArguments(){ return
	  Stream.of(Arguments.of(23.25,36.48,OperationType.SUM), 
			  Arguments.of(7.5,10,OperationType.MULTIPLY), Arguments.of(10,25,OperationType.DIVIDE) );
	  }
	  
	  private static Stream<Arguments> getTestShowOperationResultScenarioArguments(){ return
			  Stream.of(Arguments.of(29.25,OperationType.SUM), 
					  Arguments.of(75.0,OperationType.MULTIPLY), Arguments.of(4.5,OperationType.DIVIDE) );
			  }
	 

}
