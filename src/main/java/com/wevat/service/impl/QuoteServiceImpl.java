package com.wevat.service.impl;

import com.wevat.exception.InvalidResponseException;
import com.wevat.exception.QuoteNotFoundException;
import com.wevat.model.dto.PaymentDetails;
import com.wevat.model.Quote;
import com.wevat.model.dto.TransferWiseQuote;
import com.wevat.model.dto.TransferWiseQuoteRequest;
import com.wevat.model.User;
import com.wevat.respository.QuoteRepository;
import com.wevat.service.QuoteService;
import com.wevat.service.UserService;
import com.wevat.util.QuoteMapper;
import com.wevat.util.RestTemplateUtil;
import com.wevat.util.TransferWiseRequestCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Value("${transferWise.api.profile.id}")
    private String profileId;

    private UserService userService;
    private QuoteRepository quoteRepository;
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    public QuoteServiceImpl(UserService userService, QuoteRepository quoteRepository, RestTemplateUtil restTemplateUtil) {
        this.userService = userService;
        this.quoteRepository = quoteRepository;
        this.restTemplateUtil = restTemplateUtil;
    }

    @Override
    public Quote getQuoteById(String id) {
        return quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("Quote not found with Id : '" + id + "'."));
    }

    @Override
    public List<Quote> getAllQuotes() {
        return (List<Quote>) quoteRepository.findAll();
    }

    @Override
    public Quote createQuote(PaymentDetails paymentDetails) {

        User user = userService.getUserById(paymentDetails.getUserId());

        TransferWiseQuoteRequest transferWiseQuoteRequest = TransferWiseRequestCreator.create(profileId, user.getPayoutCurrency(),
                paymentDetails.getPayAmount());

        HttpEntity<Object> request = new HttpEntity<>(transferWiseQuoteRequest);
        ResponseEntity transferWiseQuoteResponse = restTemplateUtil.post(request, TransferWiseQuote.class);
        Object object = transferWiseQuoteResponse.getBody();

        if (object == null) {
            throw new InvalidResponseException("Invalid Response received from TransferWise.");
        }

        TransferWiseQuote transferWiseQuote = (TransferWiseQuote) transferWiseQuoteResponse.getBody();
        Quote quote = QuoteMapper.map(transferWiseQuote, user);
        quote = quoteRepository.save(quote);
        return quote;
    }

    @Override
    public Quote updateQuote(Quote quote) {
        Quote persistedQuote = quoteRepository.findById(quote.getId())
                .orElseThrow(() -> new QuoteNotFoundException("Quote not found with Id : '" + quote.getId() + "'."));
        persistedQuote.setTransferWiseQuoteId(quote.getTransferWiseQuoteId());
        persistedQuote.setRate(quote.getRate());
        persistedQuote.setFee(quote.getFee());
        persistedQuote.setSource(quote.getSource());
        persistedQuote.setTarget(quote.getTarget());
        persistedQuote.setTargetAmount(quote.getTargetAmount());
        persistedQuote.setSourceAmount(quote.getSourceAmount());
        return quoteRepository.save(persistedQuote);
    }

    @Override
    public void deleteQuote(String id) {
        quoteRepository.deleteById(id);
    }

}
