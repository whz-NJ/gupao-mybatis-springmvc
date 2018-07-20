package com.gupao.dal.dao;

import java.util.ArrayList;
import java.util.List;

public class Test2Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Test2Example() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdTestIsNull() {
            addCriterion("id_test is null");
            return (Criteria) this;
        }

        public Criteria andIdTestIsNotNull() {
            addCriterion("id_test is not null");
            return (Criteria) this;
        }

        public Criteria andIdTestEqualTo(Integer value) {
            addCriterion("id_test =", value, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestNotEqualTo(Integer value) {
            addCriterion("id_test <>", value, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestGreaterThan(Integer value) {
            addCriterion("id_test >", value, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_test >=", value, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestLessThan(Integer value) {
            addCriterion("id_test <", value, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestLessThanOrEqualTo(Integer value) {
            addCriterion("id_test <=", value, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestIn(List<Integer> values) {
            addCriterion("id_test in", values, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestNotIn(List<Integer> values) {
            addCriterion("id_test not in", values, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestBetween(Integer value1, Integer value2) {
            addCriterion("id_test between", value1, value2, "idTest");
            return (Criteria) this;
        }

        public Criteria andIdTestNotBetween(Integer value1, Integer value2) {
            addCriterion("id_test not between", value1, value2, "idTest");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNull() {
            addCriterion("test_name is null");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNotNull() {
            addCriterion("test_name is not null");
            return (Criteria) this;
        }

        public Criteria andTestNameEqualTo(String value) {
            addCriterion("test_name =", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotEqualTo(String value) {
            addCriterion("test_name <>", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThan(String value) {
            addCriterion("test_name >", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThanOrEqualTo(String value) {
            addCriterion("test_name >=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThan(String value) {
            addCriterion("test_name <", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThanOrEqualTo(String value) {
            addCriterion("test_name <=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLike(String value) {
            addCriterion("test_name like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotLike(String value) {
            addCriterion("test_name not like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameIn(List<String> values) {
            addCriterion("test_name in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotIn(List<String> values) {
            addCriterion("test_name not in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameBetween(String value1, String value2) {
            addCriterion("test_name between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotBetween(String value1, String value2) {
            addCriterion("test_name not between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestVrIsNull() {
            addCriterion("test_vr is null");
            return (Criteria) this;
        }

        public Criteria andTestVrIsNotNull() {
            addCriterion("test_vr is not null");
            return (Criteria) this;
        }

        public Criteria andTestVrEqualTo(String value) {
            addCriterion("test_vr =", value, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrNotEqualTo(String value) {
            addCriterion("test_vr <>", value, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrGreaterThan(String value) {
            addCriterion("test_vr >", value, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrGreaterThanOrEqualTo(String value) {
            addCriterion("test_vr >=", value, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrLessThan(String value) {
            addCriterion("test_vr <", value, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrLessThanOrEqualTo(String value) {
            addCriterion("test_vr <=", value, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrLike(String value) {
            addCriterion("test_vr like", value, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrNotLike(String value) {
            addCriterion("test_vr not like", value, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrIn(List<String> values) {
            addCriterion("test_vr in", values, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrNotIn(List<String> values) {
            addCriterion("test_vr not in", values, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrBetween(String value1, String value2) {
            addCriterion("test_vr between", value1, value2, "testVr");
            return (Criteria) this;
        }

        public Criteria andTestVrNotBetween(String value1, String value2) {
            addCriterion("test_vr not between", value1, value2, "testVr");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}