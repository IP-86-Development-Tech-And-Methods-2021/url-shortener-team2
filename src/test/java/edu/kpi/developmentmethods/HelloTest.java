package edu.kpi.developmentmethods;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloTest {

  @Test
  void simpleAlwaysGreenTest() {
    assertThat(1).isEqualTo(1);
  }
}
