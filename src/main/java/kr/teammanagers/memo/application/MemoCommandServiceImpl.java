package kr.teammanagers.memo.application;

import kr.teammanagers.memo.domain.Memo;
import kr.teammanagers.memo.dto.request.CreateMemo;
import kr.teammanagers.memo.dto.request.UpdateMemo;
import kr.teammanagers.memo.repository.MemoRepository;
import kr.teammanagers.tag.application.TagModuleService;
import kr.teammanagers.tag.domain.TagMemo;
import kr.teammanagers.tag.repository.TagMemoRepository;
import kr.teammanagers.team.domain.Team;
import kr.teammanagers.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoCommandServiceImpl implements MemoCommandService {

    private final MemoRepository memoRepository;
    private final TeamRepository teamRepository;
    private final TagMemoRepository tagMemoRepository;

    private final TagModuleService tagModuleService;

    @Override
    public void createMemo(final Long teamId, final CreateMemo request) {
        Team team = teamRepository.findById(teamId).orElseThrow(RuntimeException::new);
        Memo memo = request.toMemo();
        memo.setTeam(team);
        memoRepository.save(memo);

        request.tagList().stream()
                .map(tagModuleService::findOrCreateTag)
                .forEach(tag -> {
                    tagMemoRepository.save(
                            TagMemo.builder()
                                    .tag(tag)
                                    .memo(memo)
                                    .build()
                    );
                });
    }

    @Override
    public void updateMemo(final Long memoId, final UpdateMemo request) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(RuntimeException::new);

        updateTitle(request.title(), memo);
        updateTagMemoList(request.tagList(), memo);
        updateContent(request.content(), memo);
    }

    @Override
    public void deleteMemo(final Long memoId) {
        tagMemoRepository.findAllByMemoId(memoId)
                .forEach(tagMemo -> {
                    Long oldTagId = tagMemo.getTag().getId();
                    tagMemoRepository.delete(tagMemo);
                    tagModuleService.validateAndDeleteTagByTagId(oldTagId);
                });
        memoRepository.deleteById(memoId);
    }

    private void updateTitle(final String title, final Memo memo) {
        if (title != null) {
            memo.updateTitle(title);
        }
    }

    private void updateTagMemoList(final List<String> requestedTagMemoList, final Memo memo) {
        if (requestedTagMemoList == null) {
            return;
        }

        List<TagMemo> currentTagMemoList = tagMemoRepository.findAllByMemoId(memo.getId());
        List<String> currentTagMemoNames = currentTagMemoList.stream()
                .map(tagMemo -> tagMemo.getTag().getName())
                .toList();

        tagModuleService.addNewTagMemo(requestedTagMemoList, currentTagMemoNames, memo);
        tagModuleService.removeOldTagMemo(requestedTagMemoList, currentTagMemoList);
    }

    private void updateContent(final String content, final Memo memo) {
        if (content != null) {
            memo.updateContent(content);
        }
    }
}
