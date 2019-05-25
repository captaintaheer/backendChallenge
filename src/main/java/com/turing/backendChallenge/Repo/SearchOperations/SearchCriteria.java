package com.turing.backendChallenge.Repo.SearchOperations;

public class SearchCriteria {
    private String key;
    private SearchOperations operation;
    private Object value;
    private boolean orPredicate;

    public SearchCriteria (){

    }
    public SearchCriteria(final String key, final SearchOperations operation, final Object value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(final String orPredicate, final String key, final SearchOperations operation, final Object value) {
        super();
        this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperations.OR_PREDICATE_FLAG);
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, String operation, String prefix, String value, String suffix) {
        SearchOperations op = SearchOperations.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperations.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperations.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperations.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperations.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperations.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperations.STARTS_WITH;
                }
            }
        }
        this.key = key;
        this.operation = op;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SearchOperations getOperation() {
        return operation;
    }

    public void setOperation(SearchOperations operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isOrPredicate() {
        return orPredicate;
    }

    public void setOrPredicate(boolean orPredicate) {
        this.orPredicate = orPredicate;
    }
}
