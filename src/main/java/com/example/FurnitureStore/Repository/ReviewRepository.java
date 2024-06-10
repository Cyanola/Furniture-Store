package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.Reviews;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, UUID> {
    @Transactional
    @Modifying
    @Query(value = "Delete c FROM Reviews c WHERE c.id_review = :id",nativeQuery = true)
    void deleteReview(@Param("id") UUID id);
    @Transactional
    @Modifying
    @Query(value="INSERT INTO Reviews(id_review, id_good, id_cl, mark,review) " +
            "values(uuid_generate_v4(), :id_good, :id_cl,:mark, :review)",nativeQuery = true)
    void createReview(@Param("id_good") UUID good,@Param("id_cl") UUID client,@Param("mark") int mark,@Param("review") String review);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Reviews g SET g.mark=:mark,g.review=:review WHERE g.id_review = :id",nativeQuery = true)
    void setUpdatedAtById(@Param("id") UUID id,@Param("mark") int mark,@Param("review") String review);


    //отзыв по id
    @Query(value = "SELECT r FROM Reviews r right join good g on g.id_good=r.id_good right join client c on c.id_cl=r.id_cl WHERE r.id_review = :uuid WHERE r.id_review = :uuid",nativeQuery = true)
    Reviews findByUUID(@Param("uuid") UUID uuid);
    //список отзывов конкретного человека
    @Query(value = "SELECT r FROM Reviews r right join good g on g.id_good=r.id_good right join client c on c.id_cl=r.id_cl WHERE r.id_cl=:uuidcl",nativeQuery = true)
    List<Reviews> findReviewByUUIDcl(@Param("uuidcl") UUID uuidcl);


    //список отзывов на конкретный товар
    @Query(value = "SELECT r FROM Reviews r right join good g on g.id_good=r.id_good right join client c on c.id_cl=r.id_cl WHERE r.id_review = :uuid WHERE g.id_good = :uuid",nativeQuery = true)
  List<Reviews> findByUUIDGood(@Param("uuid") UUID uuid);
    //отзыв по id конкретного человека
    @Query(value = "SELECT r FROM Reviews r right join good g on g.id_good=r.id_good right join client c on c.id_cl=r.id_cl WHERE r.id_review = :uuid and r.id_cl=:uuidcl",nativeQuery = true)
    Reviews findByUUIDcl(@Param("uuid") UUID uuid, @Param("uuidcl") UUID uuidcl);

    //отзыв  конкретного человека на конкретный товар
    @Query(value = "SELECT r FROM Reviews r right join good g on g.id_good=r.id_good right join client c on c.id_cl=r.id_cl WHERE g.id_good= :uuid and r.id_cl=:uuidcl",nativeQuery = true)
    Reviews findByUUID(@Param("uuid") UUID uuid, @Param("uuidcl") UUID uuidcl);
}
