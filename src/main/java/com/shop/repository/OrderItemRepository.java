//주문 엔티티 조회 테스트 하기 
//JpaRepositry를 상속받는 OrderItemRepository 인터페이스 생성

package com.shop.repository;

import com.shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}