package com.exam.api.repository.mongoclient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.exam.api.entity.Movie;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.WriteModel;

import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.ReturnDocument.AFTER;

import org.bson.types.ObjectId;



@RequiredArgsConstructor
@Repository
public class ExamMovieRepositoryImpl implements ExamMongoRepository<Movie> {
    
    private static final TransactionOptions txnOptions = TransactionOptions.builder()
                                                                           .readPreference(ReadPreference.primary())
                                                                           .readConcern(ReadConcern.MAJORITY)
                                                                           .writeConcern(WriteConcern.MAJORITY)
                                                                           .build();
    private final MongoClient client;
    private MongoCollection<Movie> collection;


    @PostConstruct
    void init() {
        collection = client.getDatabase("movie_db").getCollection("movie_collection", Movie.class);
    }

	@Override
	public List<Movie> findAll() {
		return collection.find().into(new ArrayList<>());
	}

	@Override
	public Movie save(Movie movie) {
        movie.setId(new ObjectId().toString());
        collection.insertOne(movie);
        return movie;

	}

	@Override
	public List<Movie> saveAll(List<Movie> movies) {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(() -> {
                movies.forEach(p -> p.setId(new ObjectId().toString()));
                collection.insertMany(clientSession, movies);
                return movies;
            }, txnOptions);
        }
	}

	@Override
	public List<Movie> findAll(List<String> ids) {
		return collection.find(in("_id", mapToObjectIds(ids))).into(new ArrayList<>());
	}

	@Override
	public Movie findOne(String id) {
		return collection.find(eq("_id", new ObjectId(id))).first();
	}

	@Override
	public long count() {
		return collection.countDocuments();
	}

	@Override
	public long delete(String id) {
		return collection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
	}

	@Override
	public long delete(List<String> ids) {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> collection.deleteMany(clientSession, in("_id", mapToObjectIds(ids))).getDeletedCount(),
                    txnOptions);
        }
	}

	@Override
	public long deleteAll() {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> collection.deleteMany(clientSession, new BsonDocument()).getDeletedCount(), txnOptions);
        }
	}

	@Override
	public Movie update(Movie entity) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return collection.findOneAndReplace(eq("_id", entity.getId()), entity, options);
	}

	@Override
	public long update(List<Movie> movies) {
        List<WriteModel<Movie>> writes = movies.stream()
                                                 .map(p -> new ReplaceOneModel<>(eq("_id", p.getId()), p))
                                                 .collect(Collectors.toList());
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> collection.bulkWrite(clientSession, writes).getModifiedCount(), txnOptions);
        }
	}

	@Override
	public double getAverageAge() {
		// TODO Auto-generated method stub
		return 0;
	}


	
    private List<ObjectId> mapToObjectIds(List<String> ids) {
        return ids.stream().map(ObjectId::new).collect(Collectors.toList());
    }


}
