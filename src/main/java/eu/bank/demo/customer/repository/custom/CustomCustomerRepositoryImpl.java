package eu.bank.demo.customer.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import eu.bank.demo.account.entity.QAccountEntity;
import eu.bank.demo.card.entity.QCardEntity;
import eu.bank.demo.customer.entity.CustomerEntity;
import eu.bank.demo.customer.entity.QCustomerEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomCustomerRepositoryImpl implements CustomCustomerRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public CustomerEntity findByIdWithCardAndAccountFetched(@NonNull Long id) {
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        QCardEntity card = QCardEntity.cardEntity;
        QAccountEntity account = QAccountEntity.accountEntity;

        var result = queryFactory.selectFrom(customer)
                .leftJoin(customer.cardList, card).fetchJoin()
                .where(customer.id.eq(id))
                .orderBy(card.id.asc())
                .fetchOne();

        if (result == null) {
            return null;
        }

        queryFactory.selectFrom(customer)
                .leftJoin(customer.accountList, account).fetchJoin()
                .where(customer.id.eq(id))
                .orderBy(account.id.asc())
                .fetchOne();

        return result;
    }
}
