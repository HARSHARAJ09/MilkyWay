package in.SMW.IService.Imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.SMW.DTO.SubscriptionDTO;
import in.SMW.Entity.Product;
import in.SMW.Entity.Subscription;
import in.SMW.Entity.SubscriptionStatus;
import in.SMW.Entity.User;
import in.SMW.Exception.UserException;
import in.SMW.IService.ISubscriptionService;
import in.SMW.Repo.ProductRepo;
import in.SMW.Repo.SubscriptionRepo;
import in.SMW.Repo.UserRepo;
import in.SMW.Responses.SubscriptionResponse;

@Service
public class SubscriptionServiceImpl
		implements ISubscriptionService {

	@Autowired
	private SubscriptionRepo subscriptionRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ProductRepo productRepo;

	@Override
	public SubscriptionResponse createSubscription(
			Integer userId,
			SubscriptionDTO dto) {

		User user =
				userRepo.findById(userId)
						.orElseThrow(() ->
								new UserException(
										"User Not Found",
										HttpStatus.NOT_FOUND));

		Product product =
				productRepo.findById(
						dto.getProductId())
						.orElseThrow(() ->
								new UserException(
										"Product Not Found",
										HttpStatus.NOT_FOUND));

		Subscription subscription =
				Subscription.builder()
						.user(user)
						.product(product)
						.quantity(
								dto.getQuantity())
						.frequency(
								dto.getFrequency())
						.startDate(
								dto.getStartDate())
						.endDate(
								dto.getEndDate())
						.status(
								SubscriptionStatus.ACTIVE)
						.createdAt(
								LocalDateTime.now())
						.build();

		subscriptionRepo.save(
				subscription);

		return mapToResponse(
				subscription);
	}

	@Override
	public List<SubscriptionResponse>
	getMySubscriptions(
			Integer userId) {

		return subscriptionRepo
				.findByUserId(
						userId)
				.stream()
				.map(this::mapToResponse)
				.collect(
						Collectors.toList());
	}

	@Override
	public SubscriptionResponse pauseSubscription(
			Integer subscriptionId) {

		Subscription subscription =
				subscriptionRepo.findById(
						subscriptionId)
						.orElseThrow(() ->
								new UserException(
										"Subscription Not Found",
										HttpStatus.NOT_FOUND));

		subscription.setStatus(
				SubscriptionStatus.PAUSED);

		subscriptionRepo.save(
				subscription);

		return mapToResponse(
				subscription);
	}

	@Override
	public SubscriptionResponse resumeSubscription(
			Integer subscriptionId) {

		Subscription subscription =
				subscriptionRepo.findById(
						subscriptionId)
						.orElseThrow(() ->
								new UserException(
										"Subscription Not Found",
										HttpStatus.NOT_FOUND));

		subscription.setStatus(
				SubscriptionStatus.ACTIVE);

		subscriptionRepo.save(
				subscription);

		return mapToResponse(
				subscription);
	}

	@Override
	public SubscriptionResponse cancelSubscription(
			Integer subscriptionId) {

		Subscription subscription =
				subscriptionRepo.findById(
						subscriptionId)
						.orElseThrow(() ->
								new UserException(
										"Subscription Not Found",
										HttpStatus.NOT_FOUND));

		subscription.setStatus(
				SubscriptionStatus.CANCELLED);

		subscriptionRepo.save(
				subscription);

		return mapToResponse(
				subscription);
	}

	private SubscriptionResponse
	mapToResponse(
			Subscription subscription) {

		SubscriptionResponse response =
				new SubscriptionResponse();

		response.setSubscriptionId(
				subscription.getSubscriptionId());

		response.setProductId(
				subscription.getProduct()
						.getId());

		response.setProductName(
				subscription.getProduct()
						.getProductName());

		response.setQuantity(
				subscription.getQuantity());

		response.setFrequency(
				subscription.getFrequency()
						.name());

		response.setStartDate(
				subscription.getStartDate());

		response.setEndDate(
				subscription.getEndDate());

		response.setStatus(
				subscription.getStatus()
						.name());

		return response;
	}
	
	@Override
	public List<SubscriptionResponse>
	getAllSubscriptions() {

		return subscriptionRepo
				.findAll()
				.stream()
				.map(this::mapToResponse)
				.collect(
						Collectors.toList());
	}
}