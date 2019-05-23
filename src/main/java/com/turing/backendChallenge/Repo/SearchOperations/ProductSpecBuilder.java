package com.turing.backendChallenge.Repo.SearchOperations;

import com.turing.backendChallenge.Entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecBuilder {

    private List<SearchCriteria> params;
    public ProductSpecBuilder with(
            String key, String operation, Object value, String prefix, String suffix){
        SearchOperations op = SearchOperations.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperations.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperations.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperations.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperations.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Product> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification result = new ProductSearchSpec(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new ProductSearchSpec(params.get(i)))
                    : Specification.where(result).and(new ProductSearchSpec(params.get(i)));
        }

        return result;

    }
}
