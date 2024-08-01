package kr.teammanagers.tag.repository;

import kr.teammanagers.tag.domain.TagMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagMemoRepository extends JpaRepository<TagMemo, Long> {

    Boolean existsByTagId(Long tagId);

    List<TagMemo> findAllByMemoId(Long memoId);
}
