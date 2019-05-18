package com.example.demo.controller;
import com.example.demo.entity.Account;
import com.example.demo.form.AccountSearchForm;
import com.example.demo.service.AccountSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/account/search")
public class AccountSearchController {

    private final AccountSearchService service;
    private final HttpSession session;
    private static final String SESSION_SEARCH_FORM_ID ="accountSearchForm";
    private static final int DEFAULT_PAGEABLE_SIZE = 10;

    @Autowired
    public AccountSearchController(AccountSearchService accountService, HttpSession session) {
        this.service = accountService;
        this.session = session;
    }

    @RequestMapping(value = "/init")
    public String searchInit(@ModelAttribute AccountSearchForm accountSearchForm) {
        return "account/accountSearchForm";
    }

    @RequestMapping(value = "/do")
    public String search(@ModelAttribute @Validated AccountSearchForm accountSearchForm, BindingResult bindingResult, Model model) {
        // BeanValidationのエラー確認
        if (bindingResult.hasErrors()) {
            return "account/accountSearchForm";
        }

        List<Account> list = service.findAccount(accountSearchForm);
        if (Objects.isNull(list) || list.isEmpty()) {
            // 検索結果が０件ならエラー表示
            bindingResult.reject("validation.noSearchResult", "default message");
            return "account/accountSearchForm";
        }

        model.addAttribute("list", list);
        return "account/accountSearchForm";
    }
    @RequestMapping(value = "/paging/init")
    public String pagingSearchInit(@ModelAttribute AccountSearchForm accountSearchForm) {
        session.getAttribute(SESSION_SEARCH_FORM_ID);
        return "account/accountPagingSearchForm";
    }

    @RequestMapping(value = "/paging/do")
    public String pagingSearch(@ModelAttribute @Validated AccountSearchForm accountSearchForm, BindingResult bindingResult,
                               @PageableDefault(size = DEFAULT_PAGEABLE_SIZE)
                               @SortDefault.SortDefaults(
                                       {@SortDefault(
                                               sort = "accountId",
                                               direction = Sort.Direction.ASC
                                       )}) Pageable pageable,
                               Model model) {

        session.setAttribute(SESSION_SEARCH_FORM_ID, accountSearchForm);
        model.addAttribute(accountSearchForm);
        if (bindingResult.hasErrors()) {
            return "account/accountPagingSearchForm";
        }
        Page<Account> page = service.findAccount(accountSearchForm, pageable);
        List<Account> list = page.getContent();
        if (Objects.isNull(list) || list.isEmpty()) {
            bindingResult.reject("validation.noSearchResult", "default message");
            return "account/accountPagingSearchForm";
        }
        model.addAttribute("page", page);
        model.addAttribute("list", list);
        model.addAttribute("url", "/account/find");
        return "account/accountPagingSearchForm";
    }

    @RequestMapping(value = "/paging/pagenation")
    public String pagination(@ModelAttribute @Validated AccountSearchForm accountSearchForm, BindingResult bindingResult,
                             @PageableDefault(size = DEFAULT_PAGEABLE_SIZE)
                             @SortDefault.SortDefaults(
                                     {@SortDefault(
                                             sort = "accountid",
                                             direction = Sort.Direction.ASC
                                     )}) Pageable pageable,
                             Model model) {
        AccountSearchForm storedCondition = (AccountSearchForm) session.getAttribute(SESSION_SEARCH_FORM_ID);
        return this.pagingSearch(storedCondition, bindingResult, pageable, model);
    }
}