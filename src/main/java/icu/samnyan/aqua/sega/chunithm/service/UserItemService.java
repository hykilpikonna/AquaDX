package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserItemRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserItem;
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
@Service
public class UserItemService {
    private final UserItemRepository userItemRepository;

    @Autowired
    public UserItemService(UserItemRepository userItemRepository) {
        this.userItemRepository = userItemRepository;
    }


    public Optional<UserItem> getByUserAndItemId(UserData user, String itemId) {
        return userItemRepository.findTopByUserAndItemIdOrderByIdDesc(user, Integer.parseInt(itemId));
    }

    public UserItem save(UserItem userItem) {
        return userItemRepository.save(userItem);
    }

    public List<UserItem> saveAll(Iterable<UserItem> userItem) {
        return userItemRepository.saveAll(userItem);
    }

    public Page<UserItem> getByUserAndItemKind(String userId, int kind, int pageNumber, int maxCount) {
        Pageable page = PageRequest.of(pageNumber, maxCount);
        return userItemRepository.findAllByUser_Card_ExtIdAndItemKind(Long.parseLong(userId), kind, page);
    }
}
