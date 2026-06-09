package in.SMW.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<?> handleUserException(UserException exception) {

		Map<String, Object> response = new HashMap<>();

		response.put("timestamp", LocalDateTime.now());

		response.put("success", false);

		response.put("message", exception.getMessage());

		response.put("status", exception.getStatus().value());

		return new ResponseEntity<>(response, exception.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {

		Map<String, Object> response = new HashMap<>();

		response.put("timestamp", LocalDateTime.now());

		response.put("success", false);

		response.put("message", exception.getMessage());

		response.put("status", 500);

		return ResponseEntity.internalServerError().body(response);
	}
}
