package nextstep.subway.unit;

import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Sections;
import nextstep.subway.domain.Station;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SectionsTest {

    @Test
    void 구간을_추가한다() {
        // given
        Line line = new Line("2호선", "green");
        Station upStation = new Station("암사역");
        Station downStation = new Station("모란역");
        Section section = new Section(line, upStation, downStation, 10);
        Sections sections = new Sections();

        // when
        sections.addSection(section);

        // then
        assertAll(() -> {
            assertThat(sections.getSections()).hasSize(1);
            assertThat(sections.getSections()).containsExactly(section);
        });
    }

    @Test
    void 구간에_등록된_역을_조회한다() {
        // given
        Line line = new Line("2호선", "green");
        Station upStation = new Station("암사역");
        Station downStation = new Station("모란역");
        Section section = new Section(line, upStation, downStation, 10);
        Sections sections = new Sections();
        sections.addSection(section);

        // when
        List<Station> stations = sections.getStations();

        // then
        assertThat(stations).containsExactly(upStation, downStation);
    }

    @Test
    void 구간을_삭제한다() {
        // given
        Line line = new Line("2호선", "green");
        Station upStation = new Station("암사역");
        Station downStation = new Station("모란역");
        Section section = new Section(line, upStation, downStation, 10);
        Sections sections = new Sections();
        sections.addSection(section);

        // when
        sections.deleteSection(downStation);

        // then
        assertThat(sections.getSections()).isEmpty();
    }

    @Test
    void 구간_삭제_시_하행_종점역_외_다른역을_삭제하면_예외를_일으킨다() {
        // given
        Line line = new Line("2호선", "green");
        Station upStation = new Station("암사역");
        Station downStation = new Station("모란역");
        Section section = new Section(line, upStation, downStation, 10);
        Sections sections = new Sections();
        sections.addSection(section);

        // then
        assertThatIllegalArgumentException().isThrownBy(() ->
                sections.deleteSection(upStation)
        );
    }
}