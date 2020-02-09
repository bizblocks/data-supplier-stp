package com.groupstp.datasupplier.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.*;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|name")
@Table(name = "rtneoimport_im_real_estate_category")
@Entity(name = "rtneoimport$ImRealEstateCategory")
public class ImRealEstateCategory extends StandardEntity {
    private static final long serialVersionUID = -6867704067789286772L;

    @Column(name = "VIEW_ORDER")
    protected Integer viewOrder;

    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UNIT_ID")
    private ImUnit unit;


    @Column(name = "IS_LIVING")
    protected Boolean isLiving = false;

    @Column(name = "BLOCKED")
    protected Boolean blocked = false;



    @Column(name = "USABLE_WITHOUT_CADASTRAL_REAL_ESTATE")
    protected Boolean usableWithoutCadastralRealEstate = false;

    @Column(name = "USABLE_WITHOUT_CADASTRAL_LAND")
    protected Boolean usableWithoutCadastralLand = false;

    @Column(name = "SUPPORTING_DOCUMENTS")
    protected String supportingDocuments;

    @Column(name = "AUTO_APPLY")
    protected Boolean autoApply;

    @Column(name = "APPLY_EMPTY_INDICATORS")
    protected Boolean applyEmptyIndicators;

    @Column(name = "USABLE_FOR_EVERYONE")
    protected Boolean usableForEveryone;

    @Column(name = "APPLY_CALCULATION_AMOUNT")
    protected Boolean applyCalculationAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CATEGORY_ID")
    protected ImRealEstateCategory parentCategory;

    @NumberFormat(pattern = "0.00000")
    @Column(name = "RATIO", precision = 19, scale = 5)
    protected BigDecimal ratio;

    @NumberFormat(pattern = "##0.####", decimalSeparator = ",")
    @Column(name = "NORM", precision = 19, scale = 4)
    protected BigDecimal norm;

    @Column(name = "MIN_VALUE")
    protected BigDecimal minValue;

    @NumberFormat(pattern = "0.000")
    @Column(name = "CATEGORY_RATIO", precision = 19, scale = 3)
    protected BigDecimal categoryRatio;

    @Lob
    @Column(name = "CATEGORY_RATIO_SCRIPT")
    protected String categoryRatioScript;

    @Lob
    @Column(name = "CALCULATION_SCRIPT")
    protected String calculationScript;

    @Column(name = "CONFIRMATION_SCREEN")
    protected String confirmationScreen;

    public void setConfirmationScreen(String confirmationScreen) {
        this.confirmationScreen = confirmationScreen;
    }

    public String getConfirmationScreen() {
        return confirmationScreen;
    }


    public void setApplyCalculationAmount(Boolean applyCalculationAmount) {
        this.applyCalculationAmount = applyCalculationAmount;
    }

    public Boolean getApplyCalculationAmount() {
        return applyCalculationAmount;
    }


    public void setApplyEmptyIndicators(Boolean applyEmptyIndicators) {
        this.applyEmptyIndicators = applyEmptyIndicators;
    }

    public Boolean getApplyEmptyIndicators() {
        return applyEmptyIndicators;
    }


    public void setAutoApply(Boolean autoApply) {
        this.autoApply = autoApply;
    }

    public Boolean getAutoApply() {
        return autoApply;
    }


    public void setCalculationScript(String calculationScript) {
        this.calculationScript = calculationScript;
    }

    public String getCalculationScript() {
        return calculationScript;
    }


    public void setUsableForEveryone(Boolean usableForEveryone) {
        this.usableForEveryone = usableForEveryone;
    }

    public Boolean getUsableForEveryone() {
        return usableForEveryone;
    }


    public void setCategoryRatioScript(String categoryRatioScript) {
        this.categoryRatioScript = categoryRatioScript;
    }

    public String getCategoryRatioScript() {
        return categoryRatioScript;
    }


    public void setCategoryRatio(BigDecimal categoryRatio) {
        this.categoryRatio = categoryRatio;
    }

    public BigDecimal getCategoryRatio() {
        return categoryRatio;
    }


    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }


    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getRatio() {
        return ratio;
    }


    public void setNorm(BigDecimal norm) {
        this.norm = norm;
    }

    public BigDecimal getNorm() {
        return norm;
    }


    public void setSupportingDocuments(String supportingDocuments) {
        this.supportingDocuments = supportingDocuments;
    }

    public String getSupportingDocuments() {
        return supportingDocuments;
    }


    public void setUsableWithoutCadastralRealEstate(Boolean usableWithoutCadastralRealEstate) {
        this.usableWithoutCadastralRealEstate = usableWithoutCadastralRealEstate;
    }

    public Boolean getUsableWithoutCadastralRealEstate() {
        return usableWithoutCadastralRealEstate;
    }

    public void setUsableWithoutCadastralLand(Boolean usableWithoutCadastralLand) {
        this.usableWithoutCadastralLand = usableWithoutCadastralLand;
    }

    public Boolean getUsableWithoutCadastralLand() {
        return usableWithoutCadastralLand;
    }


    public void setIsLiving(Boolean isLiving) {
        this.isLiving = isLiving;
    }

    public Boolean getIsLiving() {
        return isLiving;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Boolean getBlocked() {
        return blocked;
    }


    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
    }

    public Integer getViewOrder() {
        return viewOrder;
    }


    public void setParentCategory(ImRealEstateCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public ImRealEstateCategory getParentCategory() {
        return parentCategory;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImUnit getUnit() {
        return unit;
    }

    public void setUnit(ImUnit unit) {
        this.unit = unit;
    }

    public static ImRealEstateCategory getCategoryByName(String name) {
        DataManager dataManager = AppBeans.get(DataManager.class);
        return dataManager.loadValue("select c from rtneoimport$ImRealEstateCategory c where c.name=:categoryName", ImRealEstateCategory.class)
                .parameter("categoryName", name).optional().orElse(null);
    }

    public static List<ImRealEstateCategory> getCategoriesByNames(List<String> names) {
        DataManager dataManager = AppBeans.get(DataManager.class);
        LoadContext<ImRealEstateCategory> context = LoadContext.create(ImRealEstateCategory.class)
                .setQuery(LoadContext.createQuery("select c from rtneoimport$ImRealEstateCategory c where c.name in :categoriesNames")
                        .setParameter("categoriesNames", names));
        try {
            return dataManager.loadList(context);
        } catch (NoResultException e)
        {
            return new ArrayList<>();
        }
    }

    public static List<ImRealEstateCategory> getCategoriesByNames(String names){
        List<String> list = Arrays.stream(names.split(names)).map(String::trim).collect(Collectors.toList());
        return getCategoriesByNames(list);
    }

    public boolean compare(ImRealEstateCategory other){
        if(other==null)
            return false;

        return this.getId().equals(other.getId());
    }

    public boolean contains(ImRealEstateCategory other)
    {
        if(other==null)
            return false;
        if(this.compare(other))
            return true;
        return compare(other.getParentCategory());
    }

    public boolean compare(List<ImRealEstateCategory> others){
        if(others==null)
            return false;
        for (ImRealEstateCategory rec: others
        ) {
            if(this.compare(rec))
                return true;
        }
        return false;
    }
}