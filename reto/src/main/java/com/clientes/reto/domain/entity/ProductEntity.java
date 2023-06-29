package com.clientes.reto.domain.entity;
import com.clientes.reto.domain.enums.AccountType;
import com.clientes.reto.domain.enums.State;
import jakarta.persistence.*;



import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {


    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Id
    @Column(name = "product_number", nullable = false, length = 10)
    private Integer producNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(nullable = false)
    private  Double balance;

    @Column(nullable = false)
    private Boolean deaudas;

    @Column(name = "available_balance", nullable = false)
    private  Double availableBalance;

    @Column(name = "excenta_gmf", nullable = false)
    private Boolean excentaGMF;

    @Column(name = "cration_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime creationDate;

    @Column(name = "update_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime updateDate;

    @Column(name = "id_client", nullable = false)
    private  String idClient;



    @OneToMany(mappedBy = "productEntity")
    private List<TransactionEntity> transactions;

    @ManyToOne
    @JoinColumn(name = "id_client", insertable = false, updatable = false)
    private  PersonEntity client;

    public ProductEntity(Integer id, AccountType accountType, Integer producNumber, State state, Double balance, Boolean deaudas, Double availableBalance, Boolean excentaGMF, LocalDateTime creationDate, LocalDateTime updateDate, String idClient, List<TransactionEntity> transactions, PersonEntity client) {
        this.id = id;
        this.accountType = accountType;
        this.producNumber = producNumber;
        this.state = state;
        this.balance = balance;
        this.deaudas = deaudas;
        this.availableBalance = availableBalance;
        this.excentaGMF = excentaGMF;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.idClient = idClient;
        this.transactions = transactions;
        this.client = client;
    }

    public ProductEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getProducNumber() {
        return producNumber;
    }

    public void setProducNumber(Integer producNumber) {
        this.producNumber = producNumber;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getDeaudas() {
        return deaudas;
    }

    public void setDeaudas(Boolean deaudas) {
        this.deaudas = deaudas;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Boolean getExcentaGMF() {
        return excentaGMF;
    }

    public void setExcentaGMF(Boolean excentaGMF) {
        this.excentaGMF = excentaGMF;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public PersonEntity getClient() {
        return client;
    }

    public void setClient(PersonEntity client) {
        this.client = client;
    }
}
