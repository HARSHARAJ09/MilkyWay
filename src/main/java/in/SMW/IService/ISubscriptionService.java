package in.SMW.IService;

import java.util.List;

import in.SMW.DTO.SubscriptionDTO;
import in.SMW.Responses.SubscriptionResponse;

public interface ISubscriptionService {

	SubscriptionResponse createSubscription(Integer userId, SubscriptionDTO dto);

	List<SubscriptionResponse> getMySubscriptions(Integer userId);

	SubscriptionResponse pauseSubscription(Integer subscriptionId);

	SubscriptionResponse resumeSubscription(Integer subscriptionId);

	List<SubscriptionResponse> getAllSubscriptions();

	SubscriptionResponse cancelSubscription(Integer subscriptionId);
}