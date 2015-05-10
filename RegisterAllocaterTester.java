public class RegisterAllocatorTester {

	private CFG test;

	public RegisterAllocatorTester() {
		test = genCFG();

	}

	private CFG genCFG() {
		CFG toRet = new CFG(null);

		IInstruction.ADD add;
		IInstruction.MOV mov;

		Register r0, r1, r2, r3, r4;
		r0 = Register.newRegister();
		r1 = Register.newRegister();
		r2 = Register.newRegister();
		r3 = Register.newRegister();
		r4 = Register.newRegister();

		/* StartBlock */
		BasicBlock startBlock = new BasicBlock();
		startBlock.label = "L0";

		IInstruction.MOV instr1 = new IInstruction.MOV();
		instr1.source = r1;
		instr1.dest = r0;
		startBlock.addInstruction(instr1);

		IInstruction.MOV instr2 = new IInstruction.MOV();
		instr2.source = r3;
		instr2.dest = r2;
		startBlock.addInstruction(instr2);

		IInstruction.ADD instr3 = new IInstruction.ADD();
		instr3.sourceA = r1;
		instr3.sourceB = r2;
		instr3.dest = r3;
		startBlock.addInstruction(instr3);

		/* Block 1 */
		BasicBlock block1 = new BasicBlock();
		block1.label = "L1";

		IInstruction.ADD instr4 = new IInstruction.ADD();
		instr4.sourceA = r1;
		instr4.sourceB = r2;
		instr4.dest = r2;
		block1.addInstruction(instr4);

		IInstruction.ADD instr5 = new IInstruction.ADD();
		instr5.sourceA = r2;
		instr5.sourceB = r3;
		instr5.dest = r1;
		block1.addInstruction(instr5);

		IInstruction.ADD instr6 = new IInstruction.ADD();
		instr6.sourceA = r1;
		instr6.sourceB = r3;
		instr6.dest = r4;
		block1.addInstruction(instr6);
		startBlock.next.add(block1);

		/*Block 2 */
		BasicBlock block2 = new BasicBlock();
		block2.label = "L2";

		add = new IInstruction.ADD();
		add.sourceA = r2;
		add.sourceB = r3;
		add.dest = r1;
		block2.addInstruction(add);

		add = IInstruction.ADD();
		add.sourceA = r1;
		add.sourceB = r3;
		add.dest = r2;
		block2.addInstruction(add);

		add = new IInstruction.ADD();
		add.sourceA = r1;
		add.sourceB = r2;
		add.dest = r4;
		block2.addInstruction(add);
		startBlock.next.add(block2);

		/*Block 3*/
		BasicBlock block3 = new BasicBlock();
		block3.label = "L3";

		mov = IInstruction.MOV();
		mov.source = r1;
		mov.dest = r3;
		block3.addInstruction(mov);

		block1.next.add(block3);

		/*Block 4*/
		BasicBlock block4 = new BasicBlock();
		block4.label = "L4";

		mov = IInstruction.MOV();
		mov.source = r2;
		mov.dest = r3;
		block4.addInstruction(mov);

		block1.next.add(block4);

		/*Block 5*/
		BasicBlock block5 = new BasicBlock();
		block5.label = "L5";

		add = new IInstruction.ADD();
		add.sourceA = r3;
		add.sourceB = r1;
		add.dest = r4;
		block5.addInstruction(add);

		add = new IInstruction.ADD();
		add.sourceA = r2;
		add.sourceB = r1;
		add.dest = r3;
		block5.addInstruction(add);
		block3.next.add(block5);
		block4.next.add(block5);

		/*Block6 */
		BasicBlock block6 = BasicBlock();
		block6.label = "L6";

		add = new IInstruction.ADD();
		add.sourceA = r3;
		add.sourceB = r4;
		add.dest = r1;
		block6.addInstruction(add);

		mov = new IInstruction.MOV();
		mov.source = r0;
		mov.dest = r5;
		block6.next.add(mov);

		block5.next.add(block6);
		
		toRet.exitBlock = block6;
		toRet.entryBlock = startBlock;
	}

	public CFG getCFG() {

		return test;
	}
}