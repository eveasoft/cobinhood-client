package com.eveasoft.cobinhood.api;

import com.eveasoft.cobinhood.model.CobinResponse;
import com.eveasoft.cobinhood.model.wallet.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by Guilhem on 16/02/2018.
 */
public interface CobinhoodWalletAPI {

    // Get balances of the current user:

    @GET("/v1/wallet/balances")
    Call<CobinResponse<List<Balance>>> getWalletBalances(@Header("Authorization") String authorization);


    // Get balance history for the current user:

    @GET("/v1/wallet/ledger")
    Call<CobinResponse<List<Ledger>>> getLedgerEntries(@Header("Authorization") String authorization,
                                                       @Query("page") Integer page, @Query("limit") Integer limit);


    // Get wallet deposit addresses:

    @GET("/v1/wallet/deposit_addresses")
    Call<CobinResponse<List<Address>>> getDepositAddresses(@Header("Authorization") String authorization);


    // Get wallet withdrawal addresses:

    @GET("/v1/wallet/withdrawal_addresses")
    Call<CobinResponse<List<Address>>> getWithdrawalAddresses(@Header("Authorization") String authorization);


    // Get withdrawal information:

    @GET("/v1/wallet/withdrawals/{withdrawal_id}")
    Call<CobinResponse<Withdrawal>> getWithdrawal(@Header("Authorization") String authorization, @Path("withdrawal_id") String withdrawalId);


    // Get all withdrawals:

    @GET("/v1/wallet/withdrawals")
    Call<CobinResponse<List<Withdrawal>>> getWithdrawals(@Header("Authorization") String authorization,
                                                         @Query("page") Integer page, @Query("limit") Integer limit);


    // Get deposit information:

    @GET("/v1/wallet/deposits/{deposit_id}")
    Call<CobinResponse<Deposit>> getDeposit(@Header("Authorization") String authorization, @Path("deposit_id") String depositId);


    // Get all deposits:

    @GET("/v1/wallet/deposits")
    Call<CobinResponse<List<Deposit>>> getDeposits(@Header("Authorization") String authorization);

    @GET("/v1/wallet/deposits")
    Call<CobinResponse<List<Deposit>>> getDeposits(@Header("Authorization") String authorization,
                                                   @Query("page") Integer page, @Query("limit") Integer limit);

}
