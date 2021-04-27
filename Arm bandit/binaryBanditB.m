function [value] = binaryBanditB(action)
%----------------------------------------------
% Exercise: Evaluation vs. Instruction
% Author : Pratik Shah
% Ref: Reinforcement Learning, Sutton and Barto
%----------------------------------------------
% Two actions 1 and 2
% Rewards are stochastic 1-Success/ 0-Failure
%
% For help on usage type >>help binaryBanditA
%
% >> binaryBanditB(action)
%----------------------------------------------
p = [.8 .9];
if rand < p(action)
	value = 1;
else
	value = 0;
end
end

