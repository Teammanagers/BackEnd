package kr.teammanagers.notice.application;

import kr.teammanagers.notice.domain.Notice;
import kr.teammanagers.notice.dto.NoticeDto;
import kr.teammanagers.notice.dto.request.CreateNotice;
import kr.teammanagers.notice.dto.response.GetNoticeList;
import kr.teammanagers.notice.dto.response.GetNoticeRecent;
import kr.teammanagers.notice.repository.NoticeRepository;
import kr.teammanagers.team.domain.Team;
import kr.teammanagers.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void createNotice(final Long teamId, final CreateNotice request) {
        Team team = teamRepository.findById(teamId).orElseThrow(RuntimeException::new);
        Notice notice = request.toNotice();
        notice.setTeam(team);

        noticeRepository.save(notice);
    }

    public GetNoticeList getNoticeList(final Long teamId) {
        List<NoticeDto> noticeDtoList = noticeRepository.findAllByTeamId(teamId).stream()
                .map(NoticeDto::from)
                .toList();
        return GetNoticeList.from(noticeDtoList);
    }

    public GetNoticeRecent getNoticeRecent(final Long teamId) {
        return noticeRepository.findFirstByTeamId(teamId)
                .map(NoticeDto::from)
                .map(GetNoticeRecent::from)
                .orElse(null);
    }
}
