%-------------------------------------------------------
% Author : Pratik Shah
% Date : 29 November, 2019
% Workshop on AI
% Example: Find Value Function for the given Policy 
%
% 4 states - [tl,bl,tr,br]
% 4 actions - [u,r,l,d]
% p - state transition probabilities
% P - policy
% r - reward to remain in state
%
% s,a -> s' is probabilistic and not certain
%-------------------------------------------------------

clear all;
close all;

gamma = .9; % Discount factor

% pi(a|s)=>policy(s,a,probability)
policy = csvread("example_policy.csv",1,0);
%policy = csvread("example_policy.c");
[m,n]=size(policy);
for k=1:m
	Policy(policy(k,1),policy(k,2))=policy(k,3);
end
N = max(policy(:,1)); % Number of states
M = max(policy(:,2)); % Number of actions
% ---- Prepare Policy Matrix ----------------
P = [];
for s=1:N
	P = [P; kron(circshift([1 0 0 0],s-1),Policy(s,:))];% Use shift inplace of circshift for Octave
%	P = [P; kron(shift([1 0 0 0],s-1),Policy(s,:))]; % comment this line and uncomment the previous one for Matlab
end

% State transition probabilities
% P(s'|s,a) => P(a,s',s)
% a = [up, right, left, down]
% s = [1-topleft 2-bottomleft 3-topright 4-bottomright]
% Read P matrix from CSV file
% statetransition(s',a,s,probability)
statetransition = csvread("example_state_transition.csv",1,0);
p = reshape(statetransition(:,4),4,16)';

% reward for remain in state s => reward(s,r)
reward = csvread("example_reward.csv",1,0);
r = reward(:,2);

% --------- PP Matrix --------------
PP = P*p;

% ------ Calculate Value function ----
V = inv(eye(N)-gamma*PP)*PP*r

