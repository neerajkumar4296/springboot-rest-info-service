package com.weatherinfoservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.weatherinfoservice.exceptions.NotImplementedOperationTypeException;
import com.weatherinfoservice.model.MathsInputRequest;
import com.weatherinfoservice.model.OperationType;

@Service
public class MathsOperationService {
	private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
	protected static final String NOT_IMPLEMENTED_OPERATION_TYPE = "Operation Type (is) not implemented yet";

	public double perform(MathsInputRequest inputRequest) throws Exception {
		double result;
		OperationType operationType = inputRequest.getOperationType();
		switch (operationType) {
		case SUM:
			result = inputRequest.getFirstNumber() + inputRequest.getSecondNumber();
			break;
		case MULTIPLY:
			result = inputRequest.getFirstNumber() * inputRequest.getSecondNumber();
			break;
		case DIVIDE:
			result = inputRequest.getFirstNumber() / inputRequest.getSecondNumber();
			break;
		default:
			logger.error(NOT_IMPLEMENTED_OPERATION_TYPE);
			throw new NotImplementedOperationTypeException(NOT_IMPLEMENTED_OPERATION_TYPE);
		}
		return result;
	}

	public String showOperationResult(double result, OperationType operationType) {

		if (operationType == OperationType.SUM) {
			return "Sum is:: " + result;
		} else if (operationType == OperationType.MULTIPLY) {
			return "Multiplication is:: " + result;
		} else
			return "Division is:: " + result;
	}

}
