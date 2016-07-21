package RESTCase.app.repo;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

import RESTCase.app.domain.Pricing;
import RESTCase.app.mongodb.MongoTemplateStore;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

	private final MongoTemplate mongoTemplate;
	
	public PriceRepositoryImpl() {
		this.mongoTemplate = MongoTemplateStore.getInstance().getMongoDBTemplate();
	}
	
	@Override
	public Pricing getPricingForId(int productId) {
		Query query = Query.query(Criteria.where("productId").is(productId));
		return mongoTemplate.findOne(query, Pricing.class);
	}

	@Override
	public boolean updatePricingForId(int productId, Pricing pricing) {
		Query query = Query.query(Criteria.where("productId").is(productId));
		Update update = new Update();
		update.set("amount", pricing.getAmount());
		update.set("currencyCode", pricing.getCurrencyCode());
	
		WriteResult result = mongoTemplate.updateFirst(query, update, Pricing.class);
		if(result.isUpdateOfExisting()) {
			return true;
		}
		return false;
	}

}
