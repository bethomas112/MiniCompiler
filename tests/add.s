main:
	movq $1, r2
	movq r2, r0
	movq $2, r3
	movq r3, r1
	movq $3, r4
	movq r0, r5
	addq r4, r5
	movq r5, r6
	imulq r1, r6
	movq $4, r7
	movq r6, %rax
	movq r6, %rdx
	sarq $63, %rdx
	idivq r7
	movq %rax, r8	movq $1, r9
	movq r8, r10
	subq r9, r10
	movq r10, $rax
	jmp L0
L0:
	addq $16, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
