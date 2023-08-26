package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom { //← QueryDslPredicateExcutor 인터페이스 상속

    //↓ itemNum(상품명)으로 데이터를 조회하기 위해서 By뒤에 필드명인 ItemNm을 메소드의 이름에 붙여줍니다.
    //↓ 엔티티명은 생략이 가능하므로 findItemByItemNm 대신에 findByItemNm으로 메소드명을 만들어 줍니다.
    //↓ 매개 변수로는 검색할 때 사용할 상품명 변수를 넘겨 줍니다.
    List<Item> findByItemNm(String itemNm);

    //↓ 상품을 상품명과 상품 상세 설명을 OR조건을 이용하여 조회하는 쿼리 메소드 입니다.
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);


    //↓ 파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 조회하는 쿼리 메소드입니다.
    List<Item> findByPriceLessThan(Integer price);


    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //↓ @Query 어노테이션 안에 JPQL로 작성한 쿼리문을 넣어줍니다. from 뒤에는 엔티티 클래스로 작성한 Item을 지정해주었고, Item으로부터 데이터를 select 하겠다는 것을 의미합니다.
    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")

    //↓ 파라미터에 @Param 어노테이션을 이용하여 파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정해 줄 수 있습니다.
    //↓ 현재는 itemDetial 변수를 "like % %" 사이에 ":itemDetail"로 값이 들어가도록 작성했습니다.
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value = "select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
        //← value 안에 네이티브 쿼리문을 작성하고 "nativeQuery=true"를 지정합니다.
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}