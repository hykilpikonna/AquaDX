package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.dao.userdata.UserItemRepository;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserItemService")
public class UserItemService {
    private final UserItemRepository userItemRepository;

    @Autowired
    public UserItemService(UserItemRepository userItemRepository) {
        this.userItemRepository = userItemRepository;
    }

    public UserItem save(UserItem userItem) {
        return userItemRepository.save(userItem);
    }

    public List<UserItem> saveAll(Iterable<UserItem> userItem) {
        return userItemRepository.saveAll(userItem);
    }

    public List<UserItem> getByUserId(String userId) {
        return userItemRepository.findAllByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Optional<UserItem> getByUserAndItemIdAndKind(UserData user, int itemId, int itemKind) {
        return userItemRepository.findTopByUserAndItemIdAndItemKindOrderByIdDesc(user, itemId, itemKind);
    }

    public Page<UserItem> getByUserAndItemKind(String userId, int kind, int pageNumber, int maxCount) {
        Pageable page = PageRequest.of(pageNumber, maxCount);
        return userItemRepository.findAllByUser_Card_ExtIdAndItemKind(Long.parseLong(userId), kind, page);
    }

    public Page<UserItem> getByUserId(String userId, int page, int size) {
        return userItemRepository.findByUser_Card_ExtId(Long.parseLong(userId), PageRequest.of(page, size));
    }
}
