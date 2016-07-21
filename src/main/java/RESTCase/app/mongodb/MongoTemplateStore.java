package RESTCase.app.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

/**
 * Singleton store to retrieve the mongo template object. Per http://mongodb.github.io/mongo-java-driver/2.13/getting-started/quick-tour/ 
 * the database object is thread safe and shared among threads. We shouldn't need connection pooling here.
 * @author James
 *
 */
public class MongoTemplateStore {

	private static MongoTemplateStore instance;
	
	//Ideally these should be in a configuration or application context
	private static final String MONGO_HOSTNAME = "localhost";
	private static final String DATABASE_NAME = "test";
	
	private MongoTemplate mongoTemplate;

	private MongoTemplateStore() {
		mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(MONGO_HOSTNAME), DATABASE_NAME));
	}
	
	public static MongoTemplateStore getInstance() {
		if(instance == null) {
			instance = new MongoTemplateStore();
		}
		return instance;
	}
	
	public MongoTemplate getMongoDBTemplate() {
		return mongoTemplate;
	}
}
