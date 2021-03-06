package kr.purred.fc.mfriend.repository;

import kr.purred.fc.mfriend.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlockRepositoryTest
{
	@Autowired BlockRepository blockRepository;

	@Test
	void crud ()
	{
		Block block = new Block ();

		block.setName ("Martrin");
		block.setReason ("친하지 않아서");
		block.setStartDate (LocalDate.now ());
		block.setEndDate (LocalDate.now ());

		blockRepository.save (block);

		List<Block> blockList = blockRepository.findAll ();

		assertThat (blockList.size ()).isEqualTo (1);
		assertThat (blockList.get (0).getName ()).isEqualTo ("Martrin");
	}
}