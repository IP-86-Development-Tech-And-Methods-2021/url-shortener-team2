package edu.kpi.developmentmethods.storage;

import edu.kpi.developmentmethods.dto.Example;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InMemoryStorageImplTest {

  @Test
  void checkValueSaving() {
    InMemoryStorageImpl storage = new InMemoryStorageImpl();

    storage.put("testKey", new Example("testExample", 100));
    Example value = storage.get("testKey");

    assertThat(value).isEqualTo(new Example("testExample", 100));
  }
}
